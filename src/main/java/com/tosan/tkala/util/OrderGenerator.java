package com.tosan.tkala.util;

import com.github.javafaker.Faker;
import com.tosan.tkala.domain.Order;

import java.util.Set;

public class OrderGenerator {

    private static final Faker faker = new Faker();

    public static Set<Order> createFakeOrder(int number) {
        for (int i =0; i< number; i++) {
            Order order = new Order();

        }


        return null;
    }
}
