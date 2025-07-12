package com.unicomer.paymentService.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PaymentResponseDto {

    private final String paymentId;
    private final String customerId;
    private final String accountId;
    private final Double amount;
    private final String currency;
    private final String status;
    private final LocalDateTime timestamp;
}

