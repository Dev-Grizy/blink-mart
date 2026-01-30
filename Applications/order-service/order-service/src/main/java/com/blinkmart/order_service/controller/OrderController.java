package com.blinkmart.order_service.controller;

import com.blinkmart.order_service.model.dto.CreateOrderRequest;
import com.blinkmart.order_service.model.dto.CreateOrderResponse;
import com.blinkmart.order_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public CreateOrderResponse createOrder(
            @RequestHeader("X-REQUEST-ID") String requestId,
            @Valid @RequestBody CreateOrderRequest request) {

        return orderService.createOrder(request, requestId);
    }
}

