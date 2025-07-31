package com.tosan.tkala.service.impl;

import com.tosan.tkala.domain.dto.ProductDTO;
import com.tosan.tkala.service.DataImportService;
import com.tosan.tkala.service.ProductService;
import com.tosan.tkala.util.ProductGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataImportServiceImpl implements DataImportService {

    private final ProductService productService;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void importProducts(int productCount) {

        List<ProductDTO> products = ProductGenerator.createFakeProduct(productCount);
        for (int i = 0; i < products.size(); i += 10) {
            List<ProductDTO> batch = products.subList(i, Math.min(i + 10, products.size()));

            try {
                // Each batch runs in a nested transaction
                productService.saveAll(batch);

            } catch (Exception e) {
                // Only this batch rolls back, main transaction continues
                log.error("Batch {} failed: {}", i / 10 + 1, e.getMessage());
            }
        }
    }
}
