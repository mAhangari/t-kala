package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Payment;
import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.dto.PurchaseProductDTO;
import com.tosan.tkala.domain.enumuration.PaymentStatus;
import com.tosan.tkala.repository.ProductRepository;
import com.tosan.tkala.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Product findById(Long id) {

        return null;
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
