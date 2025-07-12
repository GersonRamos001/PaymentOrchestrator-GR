package com.unicomer.paymentService.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaymentRequestDto {

    @NotBlank(message = "Customer ID must not be blank")
    private final String customerId;

    @NotBlank(message = "Account ID must not be blank")
    private final String accountId;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be positive")
    private final Double amount;

    @NotBlank(message = "Currency must not be blank")
    private final String currency;

    @NotBlank(message = "paymentMethod must not be blank")
    private final String paymentMethod;
}
