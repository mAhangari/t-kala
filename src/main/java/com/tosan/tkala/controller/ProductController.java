package com.tosan.tkala.controller;

import com.tosan.tkala.domain.dto.PurchaseProductDTO;
import com.tosan.tkala.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PutMapping(value = "/purchase-product", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> purchaseProduct(@RequestBody PurchaseProductDTO purchaseProduct) {
        productService.purchaseProduct(purchaseProduct);
        return ResponseEntity.ok().build();
    }
}
