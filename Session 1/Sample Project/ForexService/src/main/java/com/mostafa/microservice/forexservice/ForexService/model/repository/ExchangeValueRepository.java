package com.mostafa.microservice.forexservice.ForexService.model.repository;

import com.mostafa.microservice.forexservice.ForexService.model.entity.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends
        JpaRepository<ExchangeValue, Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}