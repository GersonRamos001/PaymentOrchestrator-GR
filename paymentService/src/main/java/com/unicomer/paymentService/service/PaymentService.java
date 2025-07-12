package com.unicomer.paymentService.service;


import com.unicomer.paymentService.dto.PaymentRequestDto;
import org.springframework.stereotype.Service;

public interface PaymentService {
    void validatePaymentRequest(PaymentRequestDto paymentRequestDto);
}
