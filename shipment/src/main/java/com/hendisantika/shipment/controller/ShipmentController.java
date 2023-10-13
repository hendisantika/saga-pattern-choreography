package com.hendisantika.shipment.controller;

import com.hendisantika.shipment.dto.InventoryEvent;
import com.hendisantika.shipment.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
