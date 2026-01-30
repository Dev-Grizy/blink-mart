package com.blinkmart.order_service.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderResponse {
    private Long orderId;
    private String status;
}
