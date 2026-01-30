package com.blinkmart.order_service.repository;

import com.blinkmart.order_service.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByRequestId(String requestId);
}
