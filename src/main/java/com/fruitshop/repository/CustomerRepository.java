package com.fruitshop.repository;

import com.fruitshop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerById(Long id);
}
