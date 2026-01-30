package com.blinkmart.payment.kafka;


import com.blinkmart.payment.model.event.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;


@Component
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void publishSuccess(PaymentEvent event) {
        send("payment-success", event);
    }

    public void publishFailure(PaymentEvent event) {
        send("payment-failed", event);
    }

    private void send(String topic, PaymentEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(topic, event.getOrderId().toString(), payload);
        } catch (Exception e) {
            throw new RuntimeException("Failed to publish payment event", e);
        }
    }
}
