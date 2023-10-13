package com.hendisantika.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.payment.dto.CustomerOrder;
import com.hendisantika.payment.dto.OrderEvent;
import com.hendisantika.payment.dto.PaymentEvent;
import com.hendisantika.payment.entity.Payment;
import com.hendisantika.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 8:01 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ReversePayment {

    private final PaymentRepository paymentRepository;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @KafkaListener(topics = "reversed-payments", groupId = "payments-group")
    public void reversePayment(String event) {
        try {
            PaymentEvent paymentEvent = new ObjectMapper().readValue(event, PaymentEvent.class);

            CustomerOrder order = paymentEvent.getOrder();

            // do refund..
            // update status as failed
            Iterable<Payment> payments = this.paymentRepository.findByOrderId(order.getOrderId());
            payments.forEach(p -> {
                p.setStatus("FAILED");
                this.paymentRepository.save(p);
            });

            // reverse previous task
            OrderEvent orderEvent = new OrderEvent();
            orderEvent.setOrder(paymentEvent.getOrder());
            orderEvent.setType("ORDER_REVERSED");
            this.kafkaTemplate.send("reversed-orders", orderEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
