package com.mostafa.microservices.paymentproject.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageRestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment environment;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/newPaymentOrder")
    public String receiveNewPaymentOrder(@RequestBody PaymentOrder paymentOrder) {
        logger.info("a message with detail {} has received", paymentOrder);
        ResponseEntity<ValidationResult> result = restTemplate
                .postForEntity("http://message-validator/validatePaymentOrder", paymentOrder, ValidationResult.class);

        return "test from " + environment.getProperty("local.server.port") + " validateion result is " + result.getBody();
    }

    @PostMapping("/newNegativeStatus")
    public ResponseEntity<HttpStatus> receiveNewNegativeStatus(@RequestBody NegativeStatus negativeStatus) {
        logger.info("a message with detail {} has received", negativeStatus);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }
}
