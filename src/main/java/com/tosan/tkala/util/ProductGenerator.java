package com.tosan.tkala.util;

import com.github.javafaker.Faker;
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
}
