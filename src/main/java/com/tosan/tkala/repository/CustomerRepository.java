package com.tosan.tkala.repository;

import com.tosan.tkala.Domain.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends UserRepository<Customer, Long> {
}
