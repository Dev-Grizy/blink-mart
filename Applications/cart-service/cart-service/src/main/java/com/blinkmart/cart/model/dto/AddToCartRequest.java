package com.blinkmart.cart.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddToCartRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String productId;

    @NotNull
    private Integer quantity;

    @NotNull
    private Double price;
}
