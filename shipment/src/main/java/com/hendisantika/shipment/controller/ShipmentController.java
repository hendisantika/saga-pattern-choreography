package com.hendisantika.shipment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.shipment.dto.CustomerOrder;
import com.hendisantika.shipment.dto.InventoryEvent;
import com.hendisantika.shipment.entity.Shipment;
import com.hendisantika.shipment.repository.ShipmentRepository;
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
 * Time: 8:12 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequiredArgsConstructor
@Slf4j
public class ShipmentController {

    private final ShipmentRepository shipmentRepository;

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    @KafkaListener(topics = "new-inventory", groupId = "inventory-group")
    public void shipOrder(String event) throws JsonProcessingException {
        Shipment shipment = new Shipment();
        InventoryEvent inventoryEvent = new ObjectMapper().readValue(event, InventoryEvent.class);
        CustomerOrder order = inventoryEvent.getOrder();
        try {
            if (order.getAddress() == null) {
                throw new Exception("Address not present");
            }
            shipment.setAddress(order.getAddress());
            shipment.setOrderId(order.getOrderId());
            shipment.setStatus("success");
            this.shipmentRepository.save(shipment);

            // do other shipment logic ..

        } catch (Exception e) {
            shipment.setOrderId(order.getOrderId());
            shipment.setStatus("failed");
            this.shipmentRepository.save(shipment);

            InventoryEvent reverseEvent = new InventoryEvent();
            reverseEvent.setType("INVENTORY_REVERSED");
            System.out.println(order);
            reverseEvent.setOrder(order);
            this.kafkaTemplate.send("reversed-inventory", reverseEvent);
        }
    }
}
