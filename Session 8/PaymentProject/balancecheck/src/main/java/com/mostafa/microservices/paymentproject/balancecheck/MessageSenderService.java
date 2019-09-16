package com.mostafa.microservices.paymentproject.balancecheck;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageSenderService {
    @Autowired
    private RestTemplate restTemplate;

    @Async
    @HystrixCommand(fallbackMethod = "sendValidationResultFallback",

            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")

            })
    public void sendValidationResult(PaymentOrder paymentOrder) {
        // TODO: 8/17/2019 call paymentorder-commands for CQRS pattern to save the payment order with status NOT_SENT
        restTemplate.postForEntity("http://response-sender/sendPayment", paymentOrder, Void.class);

        // TODO: 8/17/2019 Another approach can be applied using Saga design pattern. First, call paymentorder-commands
        //  for CQRS pattern to save the payment order then if postForEntity method encounter with exception, the
        //  compensating request should deposit available balance in redis. Therefore the status field would not be
        //  necessary any more and no need to update it in response sender. Moreover the resending-service can entirely
        //  be eliminated.
    }

    public void sendValidationResultFallback(PaymentOrder paymentOrder, Throwable e) {
        // TODO: 7/31/2019 should be persisted into database
        System.out.println("********in fallback " + paymentOrder);
    }
}
