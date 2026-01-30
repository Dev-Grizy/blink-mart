package com.blinkmart.payment.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private Double amount;

    @NotNull
    private String paymentMode; // UPI, CARD
}

