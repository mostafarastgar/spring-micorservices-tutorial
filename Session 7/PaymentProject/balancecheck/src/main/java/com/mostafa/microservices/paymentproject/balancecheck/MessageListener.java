package com.mostafa.microservices.paymentproject.balancecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@MessageEndpoint
@Service
class MessageListener {
    @Autowired
    private Environment environment;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private TxIdRepository txIdRepository;

    @Autowired
    private MessageSenderService messageSenderService;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleGreetings(@Payload PaymentOrder paymentOrder, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        Integer realPartition = environment.getProperty("kafka.partitions." + paymentOrder.getOriginatorBic(), Integer.class);
        if (!realPartition.equals(partition) && changeBalance(paymentOrder)) {
            paymentOrder.setValidationResult(new ValidationResult(ValidationStatus.INVALID, "Message is not in a valid partition"));
        } else if (!changeBalance(paymentOrder)) {
            paymentOrder.setValidationResult(new ValidationResult(ValidationStatus.INVALID, "payment order cannot be issued"));
        } else {
            paymentOrder.setValidationResult(new ValidationResult(ValidationStatus.VALID, ""));
        }
        messageSenderService.sendValidationResult(paymentOrder);
    }

    @Transactional
    public boolean changeBalance(PaymentOrder paymentOrder) {
        Optional<TxId> oldId = txIdRepository.findById(paymentOrder.getTxId());
        if (oldId.isPresent()) {
            return false;
        }
        TxId txId = new TxId();
        txId.setId(paymentOrder.getTxId());
        txIdRepository.save(txId);
        BankBalance bankBalance = balanceRepository.findByBic(paymentOrder.getOriginatorBic());
        if (bankBalance == null) {
            bankBalance = new BankBalance();
            bankBalance.setBalance(1000000000000d);
            bankBalance.setBic(paymentOrder.getOriginatorBic());
        }
        if (paymentOrder.getStatus().equals(PaymentStatus.WITHDRAW)) {
            if (paymentOrder.getAmount() <= bankBalance.getBalance()) {
                bankBalance.setBalance(bankBalance.getBalance() - paymentOrder.getAmount());
            } else {
                return false;
            }
        } else {
            bankBalance.setBalance(bankBalance.getBalance() + paymentOrder.getAmount());
        }
        balanceRepository.save(bankBalance);
        return true;
    }
}