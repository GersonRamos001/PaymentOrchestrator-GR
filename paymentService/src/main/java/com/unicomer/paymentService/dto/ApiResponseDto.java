package com.unicomer.paymentService.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class ApiResponseDto<T> {
    private final LocalDateTime timestamp;
    private final String message;
    private final String path;
    private final T data;
}

