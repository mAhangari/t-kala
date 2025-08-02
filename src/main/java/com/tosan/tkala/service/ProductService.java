package com.tosan.tkala.service;


import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.dto.ProductDTO;
import com.tosan.tkala.domain.dto.PurchaseProductDTO;
import com.tosan.tkala.exception.ProductNotFoundException;

import java.util.List;
import java.util.Set;

public interface ProductService {

    Product findById(Long id) throws ProductNotFoundException;

    void save(Product entity);
    void saveAll(List<ProductDTO> entities);

    void delete(Product entity);

    void purchaseProduct(PurchaseProductDTO purchaseProduct);

    ProductDTO getProduct(long productId);
}
