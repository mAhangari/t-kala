package com.tosan.tkala.util;

import com.github.javafaker.Faker;
import com.tosan.tkala.domain.OrderItem;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class OrderItemGenerator {

    private static final Faker faker = new Faker(new Random(1));

    public static Set<OrderItem> createFakeOrderItem(int num) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (int i = 0; i < num; i++) {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrderPrice(BigDecimal.valueOf(faker.number().numberBetween(1000, 4000)));
            orderItem.setQuantity(faker.number().numberBetween(2, 15));
            orderItem.setProduct(ProductGenerator.createFakeProductWithoutStore());

            orderItems.add(orderItem);
        }

        return orderItems;
    }

    public static Set<OrderItem> createFakeOrderItemWithoutProduct(int num) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (int i = 0; i < num; i++) {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrderPrice(BigDecimal.valueOf(faker.number().numberBetween(1000, 4000)));
            orderItem.setQuantity(faker.number().numberBetween(2, 15));

            orderItems.add(orderItem);
        }

        return orderItems;
    }

    public static OrderItem createFakeOrderItem() {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderPrice(BigDecimal.valueOf(faker.number().numberBetween(1000, 4000)));
        orderItem.setQuantity(faker.number().numberBetween(2, 15));
        orderItem.setProduct(ProductGenerator.createFakeProduct());

        return orderItem;
    }
}
