package com.hendisantika.order.repository;

import com.hendisantika.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 7:46 AM
 * To change this template use File | Settings | File Templates.
 */
public interface OrderRepository extends CrudRepository<Order, Long> {
}
