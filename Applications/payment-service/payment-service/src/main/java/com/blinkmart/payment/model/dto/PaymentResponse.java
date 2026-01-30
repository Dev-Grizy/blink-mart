package com.blinkmart.payment.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long orderId;
    private String status;
}

