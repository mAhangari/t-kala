package com.tosan.tkala.repository.impl;

import com.tosan.tkala.domain.Product;
import com.tosan.tkala.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl extends AbstractBaseRepository<Product, Long> implements ProductRepository {


    @PersistenceContext
    protected EntityManager em;

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    /*@Override
    public Product findById(Long aLong) {
        return em.createQuery("SELECT i FROM Product i LEFT JOIN FETCH i.orderItems LEFT JOIN FETCH i.stores WHERE i.id = :productId", Product.class)
                .setParameter("productId", aLong)
                .getSingleResult();

    }*/
}
