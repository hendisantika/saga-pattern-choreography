package com.hendisantika.inventory.repository;

import com.hendisantika.inventory.entity.Inventory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : saga-pattern-choreography
 * User: hendi
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Link : s.id/hendisantika
 * Date: 10/13/2023
 * Time: 7:32 AM
 * To change this template use File | Settings | File Templates.
 */
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Iterable<Inventory> findByItem(String item);
}
