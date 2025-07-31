package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Payment;
import com.tosan.tkala.domain.Product;
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
import javax.persistence.PersistenceContext;
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

        try {

            System.out.println(em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity));
            System.out.println(em.contains(entity));

            productRepository.insert(entity);

            Payment payment = new Payment();
            payment.setPaymentId("6378192q0");
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setIssueDate(LocalDateTime.now());

            em.persist(payment);
            em.flush();
            em.detach(payment);

            payment.setPaymentStatus(PaymentStatus.PENDING);

            System.out.println(em.contains(payment));
            Payment mergedPayment = em.merge(payment);

            System.out.println(em.contains(mergedPayment));

            System.out.println(productRepository.findById(1L));

            List<Product> productId = em.createQuery("select p from Product p where p.id =: productId", Product.class)
                    .setParameter("productId", 1L)
                    .getResultList();
            System.out.println(productId.get(0).getColor());

            System.out.println(em.contains(entity));
        } catch (Exception e) {
            // rollback
            e.printStackTrace();
        }

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
    @Transactional//(timeout = 5)
    public void purchaseProduct(PurchaseProductDTO purchaseProduct) {
        Product product = productRepository.findById(purchaseProduct.getProductId());
        if (product.getProductQuantity() < purchaseProduct.getProductCount())
            throw new RuntimeException("Product quantity is not enough!");
        product.setProductQuantity(product.getProductQuantity() - purchaseProduct.getProductCount());
    }


}
