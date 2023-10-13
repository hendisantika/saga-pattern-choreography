package com.hendisantika.inventory.controller;

import com.hendisantika.inventory.dto.InventoryEvent;
import com.hendisantika.inventory.dto.PaymentEvent;
import com.hendisantika.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 7:33 AM
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    private final KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    private final KafkaTemplate<String, PaymentEvent> kafkaPaymentTemplate;
}
