package com.blinkmart.cart.model.dto;

import com.blinkmart.cart.model.CartItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartResponse {

    private Long userId;
    private List<CartItem> items;
    private Double totalAmount;
}
