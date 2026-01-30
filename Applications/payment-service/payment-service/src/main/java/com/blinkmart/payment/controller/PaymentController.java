package com.blinkmart.payment.controller;

import com.blinkmart.payment.model.dto.PaymentRequest;
import com.blinkmart.payment.model.dto.PaymentResponse;
import com.blinkmart.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/initiate")
    public PaymentResponse initiatePayment(
            @Valid @RequestBody PaymentRequest request) {

        return paymentService.processPayment(request);
    }
}

