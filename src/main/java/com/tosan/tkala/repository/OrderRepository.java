package com.tosan.tkala.repository;

import com.tosan.tkala.domain.Order;
import com.tosan.tkala.domain.enumuration.OrderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Modifying
    @Query("UPDATE Order o SET o.orderState = :state WHERE o.id = :orderId")
    void updateOrderState(@Param("state") OrderState state, Long orderId);

    /*@Override
    @Query("FROM Order o JOIN FETCH o.orderItems JOIN FETCH o.customer WHERE o.id = :aLong")
    Optional<Order> findById(@Param("aLong") Long aLong);*/

    /*@Override
    @EntityGraph(value = "Order.withOrderItemsAndProducts")
    Optional<Order> findById(Long id);*/

    @EntityGraph(value = "Order.withOrderItemsAndProducts")
    Page<Order> findById(Long id, Pageable pageable);
}
