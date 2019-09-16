package com.mostafa.microservices.paymentproject.balancecheck;

import org.springframework.data.repository.CrudRepository;

public interface TxIdRepository extends CrudRepository<TxId, String> {

}
