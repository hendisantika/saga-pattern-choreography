package com.hendisantika.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import org.springframework.stereotype.Controller;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 8:03 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    private final PaymentRepository paymentRepository;

    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    private final KafkaTemplate<String, OrderEvent> kafkaOrderTemplate;

    @KafkaListener(topics = "new-orders", groupId = "orders-group")
    public void processPayment(String event) throws JsonProcessingException {
        log.info("Recieved event" + event);
        OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);

        CustomerOrder order = orderEvent.getOrder();
        Payment payment = new Payment();
        try {
            // save payment details in db
            payment.setAmount(order.getAmount());
            payment.setMode(order.getPaymentMode());
            payment.setOrderId(order.getOrderId());
            payment.setStatus("SUCCESS");
            this.paymentRepository.save(payment);

            // publish payment created event for inventory microservice to consume.
            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setOrder(orderEvent.getOrder());
            paymentEvent.setType("PAYMENT_CREATED");
            this.kafkaTemplate.send("new-payments", paymentEvent);
        } catch (Exception e) {
            payment.setOrderId(order.getOrderId());
            payment.setStatus("FAILED");
            paymentRepository.save(payment);

            // reverse previous task
            OrderEvent oe = new OrderEvent();
            oe.setOrder(order);
            oe.setType("ORDER_REVERSED");
            this.kafkaOrderTemplate.send("reversed-orders", orderEvent);
        }
    }
}
