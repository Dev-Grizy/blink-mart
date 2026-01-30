package com.blinkmart.cart.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private Long userId;
    private List<CartItem> items = new ArrayList<>();

    public Double getTotalAmount() {
        return items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
    }
}
