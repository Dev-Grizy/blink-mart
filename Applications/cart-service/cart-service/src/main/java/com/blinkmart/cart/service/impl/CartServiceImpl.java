package com.blinkmart.cart.service.impl;

import com.blinkmart.cart.model.Cart;
import com.blinkmart.cart.model.CartItem;
import com.blinkmart.cart.model.dto.AddToCartRequest;
import com.blinkmart.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final Duration CART_TTL = Duration.ofHours(24);

    @Override
    public Cart addItem(AddToCartRequest request) {

        String key = buildKey(request.getUserId());

        Cart cart = (Cart) redisTemplate.opsForValue().get(key);
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(request.getUserId());
        }

        Cart finalCart = cart;
        cart.getItems().stream()
                .filter(i -> i.getProductId().equals(request.getProductId()))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + request.getQuantity()),
                        () -> finalCart.getItems().add(
                                new CartItem(
                                        request.getProductId(),
                                        request.getQuantity(),
                                        request.getPrice()
                                )
                        )
                );

        redisTemplate.opsForValue().set(key, cart, CART_TTL);
        return cart;
    }

    @Override
    public Cart getCart(Long userId) {

        Cart cart = (Cart) redisTemplate.opsForValue().get(buildKey(userId));
        return cart == null ? new Cart() : cart;
    }

    @Override
    public void removeItem(Long userId, String productId) {

        String key = buildKey(userId);
        Cart cart = (Cart) redisTemplate.opsForValue().get(key);

        if (cart == null) return;

        cart.getItems().removeIf(i -> i.getProductId().equals(productId));
        redisTemplate.opsForValue().set(key, cart, CART_TTL);
    }

    private String buildKey(Long userId) {
        return "CART:" + userId;
    }
}
