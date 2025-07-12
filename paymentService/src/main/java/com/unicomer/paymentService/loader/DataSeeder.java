package com.unicomer.paymentService.loader;

import com.unicomer.paymentService.entity.*;
import com.unicomer.paymentService.repository.CustomerRepository;
import com.unicomer.paymentService.repository.PaymentMethodRepository;
import com.unicomer.paymentService.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedDatabase(
            CustomerRepository customerRepository,
            PaymentMethodRepository paymentMethodRepository,
            PaymentRepository paymentRepository
    ) {
        return args -> {
            Customer customer = Customer.builder()
                    .name("Juan Rodriguez")
                    .accounts(List.of(
                            Account.builder().name("Camera credit").number("1234567890").currency(CurrencyName.USD).build(),
                            Account.builder().name("Tv credit").number("0987654321").currency(CurrencyName.EUR).build()
                    ))
                    .build();
            customerRepository.save(customer);


            PaymentMethod creditCard = PaymentMethod.builder()
                    .name("Visa")
                    .code("VISA")
                    .type(PaymentMethodType.CREDIT_CARD)
                    .active(true)
                    .build();
            paymentMethodRepository.save(creditCard);


            Payment payment = Payment.builder()
                    .customer(customer)
                    .paymentMethod(creditCard)
                    .amount(new BigDecimal("150.00"))
                    .currency("USD")
                    .status(StatusPayment.PENDING)
                    .attempts(0)
                    .build();
            paymentRepository.save(payment);
        };
    }
}