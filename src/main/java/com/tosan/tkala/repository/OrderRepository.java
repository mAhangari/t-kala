package com.tosan.tkala.repository;

import com.tosan.tkala.domain.Order;
import com.tosan.tkala.domain.enumuration.OrderState;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order o SET o.orderState = :state WHERE o.id = :orderId")
    void updateOrderState(@Param("state") OrderState state, Long orderId);
}
