package com.mostafa.microservices.paymentproject.responsesender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding
public class ResponsesenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResponsesenderApplication.class, args);
    }

}
