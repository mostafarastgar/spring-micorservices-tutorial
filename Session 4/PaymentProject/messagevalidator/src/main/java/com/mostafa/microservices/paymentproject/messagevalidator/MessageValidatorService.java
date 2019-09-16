package com.mostafa.microservices.paymentproject.messagevalidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageValidatorService {
    @Autowired
    private Environment environment;

    @PostMapping("/validatePaymentOrder")
    public ValidationResult validatePaymentOrderMessage(@RequestBody PaymentOrder paymentOrder) {
        ValidationResult validationResult = new ValidationResult(true, "validation server port is " + environment.getProperty("local.server.port"));
        syntaxValidation(paymentOrder, validationResult);
        if(validationResult.isValid()) {
            semanticValidation(paymentOrder, validationResult);
        }
        if(validationResult.isValid()){
            checkAvailableBalance(paymentOrder, validationResult);
        }
        return validationResult;
    }

    private void checkAvailableBalance(PaymentOrder paymentOrder, ValidationResult validationResult) {

    }

    private void semanticValidation(PaymentOrder paymentOrder, ValidationResult validationResult) {

    }

    private void syntaxValidation(PaymentOrder paymentOrder, ValidationResult validationResult) {

    }
}
