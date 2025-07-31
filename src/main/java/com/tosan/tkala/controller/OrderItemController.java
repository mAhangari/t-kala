package com.tosan.tkala.controller;

import com.tosan.tkala.domain.dto.OrderItemDTO;
import com.tosan.tkala.exception.ProductNotFoundException;
import com.tosan.tkala.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderItem")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderService orderService;

    @PostMapping(value = "/create-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@RequestBody OrderItemDTO orderItemDTO) {
        try {
            orderService.placeOrder(orderItemDTO);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/save-order", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveOrder(@RequestBody OrderItemDTO orderItemDTO) {
        orderService.saveOrder(orderItemDTO);
        return ResponseEntity.ok().build();
    }
}
