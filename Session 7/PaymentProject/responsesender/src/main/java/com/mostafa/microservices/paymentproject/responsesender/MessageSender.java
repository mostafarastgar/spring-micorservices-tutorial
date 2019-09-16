package com.mostafa.microservices.paymentproject.responsesender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSender {
    @Autowired
    private BinderAwareChannelResolver resolver;

    @PostMapping("/sendPayment")
    public void paymentOrderResult(@RequestBody PaymentOrder paymentOrder){
        resolver.resolveDestination(paymentOrder.getOriginatorBic()).send(MessageBuilder.withPayload(paymentOrder)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
        if(paymentOrder.getValidationResult().check4Valid()){
            paymentOrder.setValidationResult(null);
            resolver.resolveDestination(paymentOrder.getBeneficiaryBic()).send(MessageBuilder.withPayload(paymentOrder)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
        }
    }
}
