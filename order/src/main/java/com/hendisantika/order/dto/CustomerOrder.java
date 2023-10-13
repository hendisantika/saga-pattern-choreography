package com.hendisantika.order.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 7:44 AM
 * To change this template use File | Settings | File Templates.
 */
@Data
public class CustomerOrder {
    private String item;

    private int quantity;

    private double amount;

    private String paymentMode;

    private long orderId;

    private String address;

}
