package com.unicomer.paymentService.dto;

import com.unicomer.paymentService.entity.CurrencyName;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class AccountDto implements Serializable {
    private final String accountId;
    private final CurrencyName currency;
    private final String number;


    // Additional fields can be added as needed
}
