package com.unicomer.paymentService.repository;


import com.unicomer.paymentService.entity.PaymentMethod;
import com.unicomer.paymentService.entity.PaymentMethodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    boolean existsByName(String name);

    boolean existsByType(PaymentMethodType type);
}
