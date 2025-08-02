package com.tosan.tkala.service;

import com.tosan.tkala.domain.Order;
import com.tosan.tkala.domain.dto.OrderDTO;
import com.tosan.tkala.domain.dto.OrderItemDTO;
import com.tosan.tkala.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface OrderService {

    void placeOrder(OrderItemDTO order) throws ProductNotFoundException;

    void saveOrder(OrderItemDTO order);

    void saveAll(List<Order> orders);

    void removeOrderItem(int orderId, int orderItemId);

    OrderDTO getOrder(long orderId);

    Page<OrderDTO> getOrder(long orderId, int page, int size, String sortedBy);

    Set<OrderDTO> getAllOrder();
}
