package com.blusalt.customerservice.repository;

import com.blusalt.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String email);
    Optional<Customer> findByCustomerId(String customerId);
}
