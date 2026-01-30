package com.blinkmart.order_service.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long storeId;

    @NotNull
    private Double totalAmount;
}