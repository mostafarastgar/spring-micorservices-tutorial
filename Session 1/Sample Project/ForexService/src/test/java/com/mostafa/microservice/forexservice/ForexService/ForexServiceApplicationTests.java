package com.mostafa.microservice.forexservice.ForexService;

import com.mostafa.microservice.forexservice.ForexService.model.entity.ExchangeValue;
import com.mostafa.microservice.forexservice.ForexService.model.repository.ExchangeValueRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForexServiceApplicationTests {
    @Autowired
    ExchangeValueRepository exchangeValueRepository;

    @Test
    public void contextLoads() {
        ExchangeValue byFromAndTo = exchangeValueRepository.findByFromAndTo("USD", "INR");
        Assert.assertNotNull(byFromAndTo);
    }

}
