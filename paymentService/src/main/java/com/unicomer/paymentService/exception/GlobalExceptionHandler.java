package com.unicomer.paymentService.exception;

import com.unicomer.paymentService.dto.ApiResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentValidationException.class)
    public ResponseEntity<ApiResponseDto<Void>> handlePaymentValidationException(PaymentValidationException ex, HttpServletRequest request) {
        ApiResponseDto<Void> errorResponse = ApiResponseDto.<Void>builder()
                .timestamp(LocalDateTime.now())
                .message("Validation failed: " + ex.getMessage())
                .path(request.getRequestURI())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        ApiResponseDto<Void> errorResponse = ApiResponseDto.<Void>builder()
                .timestamp(LocalDateTime.now())
                .message("Validation failed: " + errorMessage)
                .path(request.getRequestURI())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleMalformedJson(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ApiResponseDto<Void> errorResponse = ApiResponseDto.<Void>builder()
                .timestamp(LocalDateTime.now())
                .message("El formato del JSON enviado es inválido")
                .path(request.getRequestURI())
                .data(null)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}