package com.mostafa.microservices.paymentproject.messagevalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.env.Environment;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageValidatorService {
    @Autowired
    private Environment environment;

    @Autowired
    @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    @PostMapping("/validatePaymentOrder")
    public ValidationResult validatePaymentOrderMessage(@RequestBody PaymentOrder paymentOrder) {
        paymentOrder.setStatus(PaymentStatus.WITHDRAW);
        ValidationResult validationResult = new ValidationResult();
        syntaxValidation(paymentOrder, validationResult);
        if (validationResult.check4Valid()) {
            semanticValidation(paymentOrder, validationResult);
        }
        if (validationResult.check4Valid()) {
            checkAvailableBalance(paymentOrder, validationResult);
        }
        return validationResult;
    }

    private void checkAvailableBalance(PaymentOrder paymentOrder, ValidationResult validationResult) {
        int nextPartition = environment.getProperty("kafka.partitions." + paymentOrder.getOriginatorBic(), Integer.class);
        messageChannel.send(MessageBuilder
                .withPayload(paymentOrder)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(KafkaHeaders.PARTITION_ID, nextPartition)
                .build());
        validationResult.setValid(ValidationStatus.AWAITING4BALANCECHECK);
    }

    private void semanticValidation(PaymentOrder paymentOrder, ValidationResult validationResult) {
        validationResult.setValid(ValidationStatus.VALID);
    }

    private void syntaxValidation(PaymentOrder paymentOrder, ValidationResult validationResult) {
        validationResult.setValid(ValidationStatus.VALID);
    }
}
