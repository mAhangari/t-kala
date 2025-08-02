package com.tosan.tkala.util;

import com.github.javafaker.Faker;
import com.tosan.tkala.domain.Customer;

import java.util.Random;

public class UserGenerator {

    private static final Faker faker = new Faker(new Random(1));

    public static Customer createCustomer() {
        Customer customer = new Customer();

        customer.setFirstName(faker.name().firstName());
        customer.setLastName(faker.name().lastName());
        customer.setMobileNumber(faker.phoneNumber().phoneNumber());

        return customer;
    }
}
