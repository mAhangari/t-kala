package com.tosan.tkala.service;

import com.tosan.tkala.domain.dto.OrderItemDTO;
import com.tosan.tkala.exception.ProductNotFoundException;

public interface OrderService {

    void placeOrder(OrderItemDTO order) throws ProductNotFoundException;

    void saveOrder(OrderItemDTO order);
}
