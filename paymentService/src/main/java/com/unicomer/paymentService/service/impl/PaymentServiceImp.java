package com.unicomer.paymentService.service.impl;

import com.unicomer.paymentService.dto.PaymentRequestDto;
import com.unicomer.paymentService.entity.Account;
import com.unicomer.paymentService.entity.Customer;
import com.unicomer.paymentService.entity.PaymentMethod;
import com.unicomer.paymentService.repository.CustomerRepository;
import com.unicomer.paymentService.repository.PaymentMethodRepository;
import com.unicomer.paymentService.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImp implements PaymentService {

    private static final double MAXIMUM_ALLOWED_AMOUNT = 1000.0;
    private CustomerRepository customerRepository;
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public boolean validatePaymentRequest(PaymentRequestDto paymentRequestDto) {
        // validate user
        if (!verifyCustomer(paymentRequestDto.getCustomerId())) {
            log.info("Invalid customer ID: {}", paymentRequestDto.getCustomerId());
            return false;
        }

        // validate account
        if (!verifyAccount(paymentRequestDto.getAccountId(), paymentRequestDto.getCustomerId())) {
            log.info("Invalid account ID: {} for customer ID: {}", paymentRequestDto.getAccountId(), paymentRequestDto.getCustomerId());
            return false;
        }
        // validate amount
        if (!verifyAmount(paymentRequestDto.getAmount())) {
            log.info("Invalid amount: {}. It must be greater than 0 and less than or equal to {}", paymentRequestDto.getAmount(), MAXIMUM_ALLOWED_AMOUNT);
            return false;
        }

        return true;
    }


    public boolean verifyCustomer(String userId) {
        try {
            UUID uuid = UUID.fromString(userId);
            return customerRepository.existsById(uuid);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }


    /*
     * This method verifies if the account belongs to the customer.
     * It checks if the accountId exists in the customer's accounts.
     */
    public boolean verifyAccount(String accountId, String customerId) {
        try {
            UUID uuid = UUID.fromString(customerId);
            Customer customer = customerRepository.findCustomersById(uuid);
            return customer.getAccounts().stream()
                    .anyMatch(account -> account.getId().toString().equals(accountId));
        } catch (Exception e) {
            return false;
        }
    }

    /*
     * This method verifies if the account is amount is not negative and not zero and not bigger
     * than the maximum allowed amount.
     */
    public boolean verifyAmount(double amount) {
    if (amount <= 0 || amount > MAXIMUM_ALLOWED_AMOUNT) {
        return false;

    }
        return true;
    }




}
