package com.tosan.tkala.repository.impl;

import com.tosan.tkala.domain.Product;
import com.tosan.tkala.repository.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl extends AbstractBaseRepository<Product, Long> implements ProductRepository {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

}
