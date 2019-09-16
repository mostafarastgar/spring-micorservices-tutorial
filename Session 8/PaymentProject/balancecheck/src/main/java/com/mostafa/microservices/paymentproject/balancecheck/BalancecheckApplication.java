package com.mostafa.microservices.paymentproject.balancecheck;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({Sink.class})
@EnableRedisRepositories
@EnableAsync
@EnableHystrixDashboard
@EnableCircuitBreaker
public class BalancecheckApplication {
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule loadBlancingRule() {
        return new AvailabilityFilteringRule();
    }

    @Bean
    public IPing pingConfiguration() {
        String pingPath = "/actuator/health";
        IPing ping = new PingUrl(false, pingPath);
        return ping;

    }

    public static void main(String[] args) {
        SpringApplication.run(BalancecheckApplication.class, args);
    }

}
