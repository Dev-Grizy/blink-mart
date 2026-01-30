package com.blinkmart.payment.service.impl;

import com.blinkmart.payment.kafka.PaymentEventProducer;
import com.blinkmart.payment.model.dto.PaymentRequest;
import com.blinkmart.payment.model.dto.PaymentResponse;
import com.blinkmart.payment.model.event.PaymentEvent;
import com.blinkmart.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentEventProducer producer;
    private final Random random = new Random();

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {

        boolean success = random.nextBoolean();

        PaymentEvent event = PaymentEvent.builder()
                .orderId(request.getOrderId())
                .status(success ? "SUCCESS" : "FAILED")
                .build();

        if (success) {
            producer.publishSuccess(event);
        } else {
            producer.publishFailure(event);
        }

        return PaymentResponse.builder()
                .orderId(request.getOrderId())
                .status(event.getStatus())
                .build();
    }
}
