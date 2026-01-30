package com.blinkmart.order_service.service.impl;

import com.blinkmart.order_service.model.dto.CreateOrderRequest;
import com.blinkmart.order_service.model.dto.CreateOrderResponse;
import com.blinkmart.order_service.model.entity.Order;
import com.blinkmart.order_service.model.enums.OrderStatus;
import com.blinkmart.order_service.model.enums.PaymentStatus;
import com.blinkmart.order_service.repository.OrderRepository;
import com.blinkmart.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request, String requestId) {

        return orderRepository.findByRequestId(requestId)
                .map(existing -> CreateOrderResponse.builder()
                        .orderId(existing.getOrderId())
                        .status(existing.getOrderStatus().name())
                        .build())
                .orElseGet(() -> {
                    Order order = Order.builder()
                            .userId(request.getUserId())
                            .storeId(request.getStoreId())
                            .totalAmount(request.getTotalAmount())
                            .orderStatus(OrderStatus.CREATED)
                            .paymentStatus(PaymentStatus.PENDING)
                            .requestId(requestId)
                            .createdAt(LocalDateTime.now())
                            .build();

                    Order saved = orderRepository.save(order);

                    return CreateOrderResponse.builder()
                            .orderId(saved.getOrderId())
                            .status(saved.getOrderStatus().name())
                            .build();
                });
    }
}
