package com.hendisantika.shipment.repository;

import com.hendisantika.shipment.entity.Shipment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 8:11 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
}
