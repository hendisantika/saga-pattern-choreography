package com.hendisantika.order.controller;

import com.hendisantika.order.dto.CustomerOrder;
import com.hendisantika.order.dto.OrderEvent;
import com.hendisantika.order.entity.Order;
import com.hendisantika.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 7:50 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping("/orders")
    public void createOrder(@RequestBody CustomerOrder customerOrder) {
        Order order = new Order();
        try {
            // save order in database
            order.setAmount(customerOrder.getAmount());
            order.setItem(customerOrder.getItem());
            order.setQuantity(customerOrder.getQuantity());
            order.setStatus("CREATED");
            order = this.orderRepository.save(order);

            customerOrder.setOrderId(order.getId());

            // publish order created event for payment microservice to consume.
            OrderEvent event = new OrderEvent();
            event.setOrder(customerOrder);
            event.setType("ORDER_CREATED");
            this.kafkaTemplate.send("new-orders", event);
        } catch (Exception e) {
            order.setStatus("FAILED");
            this.orderRepository.save(order);
        }
    }
}
