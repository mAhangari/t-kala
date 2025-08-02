package com.tosan.tkala.util;

import com.github.javafaker.Faker;
import com.tosan.tkala.domain.Order;
import com.tosan.tkala.domain.enumuration.OrderState;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderGenerator {

    private static final Faker faker = new Faker();

    public static List<Order> createFakeOrder(int number) {
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Order order = new Order();

            order.setOrderState(OrderState.ORDER);
            order.setOrderItems(OrderItemGenerator.createFakeOrderItem(faker.number().numberBetween(3, 7)));
            order.setTrackerId(UUID.randomUUID().toString());
            order.setCustomer(UserGenerator.createCustomer());

            orders.add(order);
        }


        return orders;
    }
}
