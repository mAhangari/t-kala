package com.tosan.tkala.service;


import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.dto.PurchaseProductDTO;

public interface ProductService {

    Product findById(Long id);

    void save(Product entity);

    void delete(Product entity);

    void purchaseProduct(PurchaseProductDTO purchaseProduct);
}
