package com.blinkmart.payment.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Map<String, String> handle(Exception ex) {
        return Map.of(
                "error", "PAYMENT_ERROR",
                "message", ex.getMessage()
        );
    }
}
