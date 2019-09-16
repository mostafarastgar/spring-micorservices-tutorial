package com.mostafa.microservices.paymentproject.balancecheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestTemplate;

@MessageEndpoint
class MessageListener {
    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleGreetings(@Payload PaymentOrder paymentOrder, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        Integer realPartition = environment.getProperty("kafka.partitions." + paymentOrder.getOriginatorBic(), Integer.class);
        if(realPartition == partition){
//            todo: check available balance in cache server
            paymentOrder.setValidationResult(new ValidationResult(ValidationStatus.VALID, ""));
            sendValidationResult(paymentOrder);
        } else {
            paymentOrder.setValidationResult(new ValidationResult(ValidationStatus.INVALID, "Message is not in a valid partition"));
            sendValidationResult(paymentOrder);
        }
    }

    @Async
    void sendValidationResult(PaymentOrder paymentOrder){
        restTemplate.postForEntity("http://message-processor/paymentOrderResult", paymentOrder, String.class);
    }
}