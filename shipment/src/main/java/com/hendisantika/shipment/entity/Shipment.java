package com.hendisantika.shipment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 8:10 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Data
public class Shipment {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String address;


    @Column
    private String status;


    @Column
    private long orderId;

}
