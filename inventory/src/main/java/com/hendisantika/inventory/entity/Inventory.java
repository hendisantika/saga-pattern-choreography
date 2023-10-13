package com.hendisantika.inventory.entity;

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
 * Time: 7:29 AM
 * To change this template use File | Settings | File Templates.
 */
@Data
@Entity
public class Inventory {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private int quantity;

    @Column
    private String item;
}
