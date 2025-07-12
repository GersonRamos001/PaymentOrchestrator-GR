package com.unicomer.paymentService.util;

import com.unicomer.paymentService.service.impl.PaymentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
        "payment.validation.maximum-allowed-amount=1000.0"
})
class PaymentServiceImpTest {

    @Autowired
    private PaymentServiceImp paymentService;

    @Test
    void testVerifyAmount_ValidAmount_ReturnsTrue() {
        assertTrue(paymentService.verifyAmount(500.0));
    }

    @Test
    void testVerifyAmount_AmountEqualToMaximum_ReturnsTrue() {
        assertTrue(paymentService.verifyAmount(1000.0));
    }

    @Test
    void testVerifyAmount_AmountGreaterThanMaximum_ReturnsFalse() {
        assertFalse(paymentService.verifyAmount(1001.0));
    }

    @Test
    void testVerifyAmount_ZeroAmount_ReturnsFalse() {
        assertFalse(paymentService.verifyAmount(0.0));
    }

    @Test
    void testVerifyAmount_NegativeAmount_ReturnsFalse() {
        assertFalse(paymentService.verifyAmount(-100.0));
    }
}