package com.blinkmart.payment.model.event;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentEvent {
    private Long orderId;
    private String status; // SUCCESS / FAILED
}

