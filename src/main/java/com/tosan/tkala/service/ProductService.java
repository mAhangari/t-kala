package com.tosan.tkala.service;


import com.tosan.tkala.domain.Product;

public interface ProductService {

    Product findById(Long id);

    void save(Product entity);

    void delete(Product entity);
}
