package com.blinkmart.payment.service;


import com.blinkmart.payment.model.dto.PaymentRequest;
import com.blinkmart.payment.model.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
}
