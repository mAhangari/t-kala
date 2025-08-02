package com.tosan.tkala.util;

import com.github.javafaker.Faker;
import com.tosan.tkala.domain.Store;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class StoreGenerator {

    private static final Faker faker = new Faker(new Random(1));

    public static Set<Store> createFakeStore(int num) {
        Set<Store> stores = new HashSet<>();
        for (int i = 0; i < num; i++) {
            Store store = new Store();

            store.setName(faker.company().name());

            stores.add(store);
        }

        return stores;
    }
}
