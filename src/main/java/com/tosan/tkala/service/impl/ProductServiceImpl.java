package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.Payment;
import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.enumuration.PaymentStatus;
import com.tosan.tkala.repository.ProductRepository;
import com.tosan.tkala.repository.impl.ProductRepositoryImpl;
import com.tosan.tkala.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final EntityManagerFactory emf;

    public ProductServiceImpl(EntityManagerFactory emf) {
        this.productRepository = new ProductRepositoryImpl(emf);
        this.emf = emf;
    }

    @Override
    public Product findById(Long id) {

        return null;
    }

    @Override
    public void save(Product entity) {

        try {

            productRepository.getEm().getTransaction().begin();
            System.out.println(emf.getPersistenceUnitUtil().getIdentifier(entity));
            System.out.println(productRepository.getEm().contains(entity));

            productRepository.insert(entity);

            Payment payment = new Payment();
            payment.setPaymentId("6378192q0");
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
            payment.setIssueDate(LocalDateTime.now());

            productRepository.getEm().persist(payment);
            productRepository.getEm().flush();
            productRepository.getEm().detach(payment);

            payment.setPaymentStatus(PaymentStatus.PENDING);

            System.out.println(productRepository.getEm().contains(payment));
            Payment mergedPayment = productRepository.getEm().merge(payment);

            System.out.println(productRepository.getEm().contains(mergedPayment));

            System.out.println(productRepository.findById(1L));

            List<Product> productId = productRepository.getEm().createQuery("select p from Product p where p.id =: productId", Product.class)
                    .setParameter("productId", 1L)
                    .getResultList();
            System.out.println(productId.get(0).getColor());

            System.out.println(productRepository.getEm().contains(entity));
            productRepository.getEm().getTransaction().commit();
            System.out.println(productRepository.getEm().contains(entity));
        } catch (Exception e) {
            // rollback
        } finally {

            productRepository.getEm().close();
        }

    }

    @Override
    public void delete(Product entity) {
        productRepository.delete(entity);
    }
}
