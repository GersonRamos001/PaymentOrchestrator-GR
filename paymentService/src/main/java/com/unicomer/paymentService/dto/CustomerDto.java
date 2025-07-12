package com.unicomer.paymentService.dto;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomerDto {
    private final String customerId;
    private final List<AccountDto> accounts;
}
