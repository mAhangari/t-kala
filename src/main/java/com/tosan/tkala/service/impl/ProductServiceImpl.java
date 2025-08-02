package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Payment;
import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.Store;
import com.tosan.tkala.domain.dto.ProductDTO;
import com.tosan.tkala.domain.dto.PurchaseProductDTO;
import com.tosan.tkala.domain.enumuration.PaymentStatus;
import com.tosan.tkala.exception.ProductNotFoundException;
import com.tosan.tkala.repository.ProductRepository;
import com.tosan.tkala.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUtil;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @PersistenceContext
    private final EntityManager em;

    @Transactional//(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Product findById(Long id) throws ProductNotFoundException {
        Product product = em.find(Product.class, id);
        log.info("productName: {}", product.getName());
        if (product == null)
            throw new ProductNotFoundException("InvalidProductId", "Product with id: " + id + " not found.");
        return product;
    }

    @Override
    @Transactional
    public void save(Product entity) {
        productRepository.insert(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAll(List<ProductDTO> entities) {
        productRepository.insertAll(
                entities.stream()
                        .map(productDTO -> new Product(productDTO.getName(), productDTO.getColor(), productDTO.getProductQuantity()))
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }

    @Override
//    @Transactional//(timeout = 5)
    public void purchaseProduct(PurchaseProductDTO purchaseProduct) {

        productRepository.findById(purchaseProduct.getProductId());

        /*if (product.getProductQuantity() < purchaseProduct.getProductCount())
            throw new RuntimeException("Product quantity is not enough!");
        product.setProductQuantity(product.getProductQuantity() - purchaseProduct.getProductCount());*/
    }

    @Override
    @Transactional
    public ProductDTO getProduct(long productId) {

        Product product = productRepository.findById(productId);
        return fromProduct(product);
    }

    private ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setColor(product.getColor());
        productDTO.setProductQuantity(product.getProductQuantity());
        productDTO.setAvailableStores(product.getStores().stream().map(Store::getName).collect(Collectors.toSet()));
        productDTO.setOrderedQuantity(product.getOrderItems().size());

        return productDTO;
    }


}
