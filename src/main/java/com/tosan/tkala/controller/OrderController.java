package com.tosan.tkala.controller;

import com.tosan.tkala.domain.dto.OrderDTO;
import com.tosan.tkala.domain.dto.OrderItemDTO;
import com.tosan.tkala.exception.ProductNotFoundException;
import com.tosan.tkala.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

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

    @DeleteMapping(value = "/remove-order-item/{orderId}/{orderItemId}")
    public ResponseEntity<Void> removeOrderItem(@PathVariable(name = "orderId") int orderId,
                                                @PathVariable(name = "orderItemId") int orderItemId) {

        orderService.removeOrderItem(orderId, orderItemId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-order/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable(name = "orderId") int orderId) {


        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/get-all-order")
    public ResponseEntity<Set<OrderDTO>> getAllOrder() {


        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @GetMapping("/get-pagable-order/{orderId}")
    public ResponseEntity<Page<OrderDTO>> getOrder(@PathVariable(name = "orderId") int orderId,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size,
                                                   @RequestParam(defaultValue = "asc") String sortBy) {


        return ResponseEntity.ok(orderService.getOrder(orderId, page, size, sortBy));
    }
}
