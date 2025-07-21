package com.tosan.tkala.repository.impl;

import com.tosan.tkala.domain.Product;
import com.tosan.tkala.repository.ProductRepository;

import javax.persistence.EntityManagerFactory;

public class ProductRepositoryImpl extends AbstractBaseRepository<Product, Long> implements ProductRepository {

    public ProductRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Product.class);
    }


}
