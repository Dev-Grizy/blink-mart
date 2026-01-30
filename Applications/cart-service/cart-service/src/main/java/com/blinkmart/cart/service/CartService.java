package com.blinkmart.cart.service;

import com.blinkmart.cart.model.Cart;
import com.blinkmart.cart.model.dto.AddToCartRequest;

public interface CartService {

    Cart addItem(AddToCartRequest request);

    Cart getCart(Long userId);

    void removeItem(Long userId, String productId);
}