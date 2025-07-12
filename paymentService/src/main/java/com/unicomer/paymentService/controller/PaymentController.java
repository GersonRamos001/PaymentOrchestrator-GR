package com.unicomer.paymentService.controller;

import com.unicomer.paymentService.dto.ApiResponseDto;
import com.unicomer.paymentService.dto.PaymentRequestDto;
import com.unicomer.paymentService.dto.PaymentResponseDto;
import com.unicomer.paymentService.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;
    @Operation(
            summary = "Process a payment",
            description = "Processes a payment request for a given customer and account. " +
                    "Returns the payment details if successful or an error message if validation fails."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Payment processed successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid payment request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            )
    })
    @PostMapping
    public ResponseEntity<ApiResponseDto<PaymentResponseDto>> processPayment(
            @Valid @RequestBody PaymentRequestDto requestDto,
            HttpServletRequest request) {
        log.info("Received payment request for customerId: {}, accountId: {}, amount: {}, currency: {}",
                requestDto.getCustomerId(), requestDto.getAccountId(), requestDto.getAmount(), requestDto.getCurrency());

        paymentService.validatePaymentRequest(requestDto);

        PaymentResponseDto paymentResponse = PaymentResponseDto.builder()
                .paymentId("paymentId")
                .customerId(requestDto.getCustomerId())
                .accountId(requestDto.getAccountId())
                .amount(requestDto.getAmount())
                .currency(requestDto.getCurrency())
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();

        ApiResponseDto<PaymentResponseDto> response = ApiResponseDto.<PaymentResponseDto>builder()
                .timestamp(LocalDateTime.now())
                .message("Payment processed successfully")
                .path(request.getRequestURI())
                .data(paymentResponse)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
