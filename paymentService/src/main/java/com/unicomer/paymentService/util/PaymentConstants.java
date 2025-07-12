package com.unicomer.paymentService.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
    * This class holds the constants used for payment validation.
    * in case new business rules are added, they can be configured here.
 */

@Component
@ConfigurationProperties(prefix = "payment.validation")
@Data

public class PaymentConstants {
    private Double maximumAllowedAmount;
}
