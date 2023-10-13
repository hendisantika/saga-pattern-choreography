package com.hendisantika.shipment.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 8:09 AM
 * To change this template use File | Settings | File Templates.
 */
@Data
public class InventoryEvent {
    private String type;

    private CustomerOrder order;
}
