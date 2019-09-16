package com.mostafa.microservices.paymentproject.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/newPaymentOrder")
    public ResponseEntity<HttpStatus> receiveNewPaymentOrder(@RequestBody PaymentOrder paymentOrder){
        logger.info("a message with detail {} has received", paymentOrder);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/newNegativeStatus")
    public ResponseEntity<HttpStatus> receiveNewNegativeStatus(@RequestBody NegativeStatus negativeStatus){
        logger.info("a message with detail {} has received", negativeStatus);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
