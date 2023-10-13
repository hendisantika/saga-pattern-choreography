package com.hendisantika.payment.service;

import com.hendisantika.payment.dto.OrderEvent;
import com.hendisantika.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
