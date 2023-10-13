package com.hendisantika.payment.repository;

import com.hendisantika.payment.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 8:00 AM
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findByOrderId(long orderId);

}
