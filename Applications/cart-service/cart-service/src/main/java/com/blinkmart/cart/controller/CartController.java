package com.blinkmart.cart.controller;

import com.blinkmart.cart.model.Cart;
import com.blinkmart.cart.model.dto.AddToCartRequest;
import com.blinkmart.cart.model.dto.CartResponse;
import com.blinkmart.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/items")
    public CartResponse addItem(@Valid @RequestBody AddToCartRequest request) {

        Cart cart = cartService.addItem(request);
        return CartResponse.builder()
                .userId(cart.getUserId())
                .items(cart.getItems())
                .totalAmount(cart.getTotalAmount())
                .build();
    }

    @GetMapping("/{userId}")
    public CartResponse getCart(@PathVariable Long userId) {

        Cart cart = cartService.getCart(userId);
        return CartResponse.builder()
                .userId(cart.getUserId())
                .items(cart.getItems())
                .totalAmount(cart.getTotalAmount())
                .build();
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public void removeItem(
            @PathVariable Long userId,
            @PathVariable String productId) {

        cartService.removeItem(userId, productId);
    }
}
