package com.mostafa.microservices.paymentproject.messageprocessor;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient()
public class MessageprocessorApplication {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
//
//    @Bean
//    public IRule loadBlancingRule() {
////        return new RoundRobinRule();
//        return new AvailabilityFilteringRule();
////        return new BestAvailableRule();
//    }

    @Bean
    public IPing pingConfiguration() {
        String pingPath = "/actuator/health";
        IPing ping = new PingUrl(false, pingPath);
        return ping;

    }

    public static void main(String[] args) {
        SpringApplication.run(MessageprocessorApplication.class, args);
    }

}
