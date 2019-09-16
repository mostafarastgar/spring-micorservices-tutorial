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
        restTemplate.postForEntity("http://response-sender/sendPayment", paymentOrder, Void.class);
    }

    public void sendValidationResultFallback(PaymentOrder paymentOrder, Throwable e) {
        // TODO: 7/31/2019 should be persisted into database
        System.out.println("********in fallback " + paymentOrder);
    }
}
