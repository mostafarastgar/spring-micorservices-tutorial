package com.mostafa.microservices.paymentproject.balancecheck;

import org.springframework.data.repository.CrudRepository;

public interface BalanceRepository extends CrudRepository<BankBalance, Long> {
    BankBalance findByBic(String bic);
}
