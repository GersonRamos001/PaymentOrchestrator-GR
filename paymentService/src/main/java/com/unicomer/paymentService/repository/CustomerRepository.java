package com.unicomer.paymentService.repository;

import com.unicomer.paymentService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Customer findCustomersById(UUID uuid);
}
