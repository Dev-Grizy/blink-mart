package com.blinkmart.order_service.service;

import com.blinkmart.order_service.model.dto.CreateOrderRequest;
import com.blinkmart.order_service.model.dto.CreateOrderResponse;

public interface OrderService {
    CreateOrderResponse createOrder(CreateOrderRequest request, String requestId);
}
