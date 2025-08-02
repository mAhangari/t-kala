package com.tosan.tkala.util;

import com.github.javafaker.Faker;
import com.tosan.tkala.domain.OrderItem;
import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.Store;
import com.tosan.tkala.domain.dto.ProductDTO;

import java.util.*;

public class ProductGenerator {

    private static final Faker faker = new Faker(new Random(1));

    public static List<ProductDTO> createFakeProduct(int number) {

        List<ProductDTO> products = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ProductDTO product = new ProductDTO();

            product.setProductQuantity(faker.number().numberBetween(1, 10));
            product.setColor(faker.commerce().color());
            product.setName(faker.commerce().productName());

            products.add(product);
        }

        return products;
    }

    public static Product createFakeProduct() {
        Product product = new Product();

        product.setProductQuantity(faker.number().numberBetween(1, 10));
        product.setColor(faker.commerce().color());
        product.setName(faker.commerce().productName());

        Set<OrderItem> fakeOrderItem = OrderItemGenerator.createFakeOrderItemWithoutProduct(3);
        fakeOrderItem.forEach(orderItem -> orderItem.setProduct(product));
        product.setOrderItems(fakeOrderItem);

        Set<Store> fakeStore = StoreGenerator.createFakeStore(4);
        fakeStore.forEach(store -> store.setProduct(product));
        product.setStores(fakeStore);

        return product;
    }

    public static Product createFakeProductWithoutStore() {
        Product product = new Product();

        product.setProductQuantity(faker.number().numberBetween(1, 10));
        product.setColor(faker.commerce().color());
        product.setName(faker.commerce().productName());

        return product;
    }
}
