package com.mostafa.springboot.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "rates", path = "rates")
public interface ExchangeValueRepository extends
        JpaRepository<ExchangeValue, Long> {
//    comment bellow line to expose it
    @RestResource(exported = false)
    ExchangeValue findByFromAndTo(String from, String to);

}