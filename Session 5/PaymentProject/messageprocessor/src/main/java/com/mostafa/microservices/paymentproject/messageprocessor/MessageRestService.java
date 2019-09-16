package com.mostafa.microservices.paymentproject.messageprocessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MessageRestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RestTemplate restTemplate;
    @Value("${test.data}")
    private String data;

    @GetMapping
    public String test(){
        return data;
    }

    @PostMapping("/newPaymentOrder")
    public String receiveNewPaymentOrder(@RequestBody PaymentOrder paymentOrder) {
        ResponseEntity<ValidationResult> result = restTemplate
                .postForEntity("http://message-validator/validatePaymentOrder", paymentOrder, ValidationResult.class);

        return "OK";
    }

    @PostMapping("/newNegativeStatus")
    public ResponseEntity<HttpStatus> receiveNewNegativeStatus(@RequestBody NegativeStatus negativeStatus) {
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping("paymentOrderResult")
    public String paymentOrderResult(@RequestBody PaymentOrder paymentOrder){
        logger.info("after balance check {}", paymentOrder);
        return "OK";
    }
}
