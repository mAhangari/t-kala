package com.tosan.tkala.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tosan.tkala.domain.Order;
import com.tosan.tkala.domain.OrderItem;
import com.tosan.tkala.domain.Product;
import com.tosan.tkala.domain.TransactionHistory;
import com.tosan.tkala.domain.dto.OrderDTO;
import com.tosan.tkala.domain.dto.OrderItemDTO;
import com.tosan.tkala.domain.enumuration.OrderState;
import com.tosan.tkala.exception.ProductNotFoundException;
import com.tosan.tkala.repository.OrderItemRepository;
import com.tosan.tkala.repository.OrderRepository;
import com.tosan.tkala.service.OrderService;
import com.tosan.tkala.service.ProductService;
import com.tosan.tkala.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final TransactionHistoryService historyService;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public void placeOrder(OrderItemDTO orderItem) throws ProductNotFoundException {
        String trackerId = UUID.randomUUID().toString();
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setTrackerId(trackerId);
        transactionHistory.setUserId(String.valueOf(Thread.currentThread().getId()));
        try {
            transactionHistory.setData(new ObjectMapper().writeValueAsString(orderItem));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        historyService.log(transactionHistory);

        Product product = productService.findById(orderItem.getProductId().longValue());

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(product);
        orderItem1.setQuantity(orderItem.getQuantity());
        orderItem1.setOrderPrice(BigDecimal.valueOf(1000));

        Order order = new Order();
        order.getOrderItems().add(orderItem1);
        order.setOrderState(OrderState.ORDER);
        order.setTrackerId(trackerId);

        orderItem1.setOrder(order);


        orderRepository.save(order);

        orderRepository.updateOrderState(OrderState.SHOPPING_CART, order.getId());


    }

    @Override
    public void saveOrder(OrderItemDTO orderItemDTO) {
        Product product = null;
        try {
            product = productService.findById(orderItemDTO.getProductId().longValue());
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

        product.setProductQuantity(25);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(product);
        orderItem1.setQuantity(orderItemDTO.getQuantity());
        orderItem1.setOrderPrice(BigDecimal.valueOf(1000));

        Order order = new Order();
        order.getOrderItems().add(orderItem1);
        order.setOrderState(OrderState.ORDER);
        order.setTrackerId(UUID.randomUUID().toString());

        orderItem1.setOrder(order);

        orderRepository.save(order);
    }

    @Override
    public void saveAll(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    @Override
    @Transactional
    public void removeOrderItem(int orderId, int orderItemId) {
        Order order = orderRepository.findById((long) orderId).get();
        OrderItem orderItem = new OrderItem();
        orderItem.setId((long) orderItemId);
        System.out.println(order.getOrderItems().size());
        orderItemRepository.delete(orderItem);

    }

    @Override
    @Transactional
    public OrderDTO getOrder(long orderId) {
        Order order = orderRepository.findById(orderId).get();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderState(order.getOrderState());
        orderDTO.setCreatedDate(order.getCreatedDate());
        orderDTO.setOrderItems(fromOrderItem(order.getOrderItems()));

        return orderDTO;
    }

    @Override
    @Transactional
    public Page<OrderDTO> getOrder(long orderId, int page, int size, String sortedBy) {
        Page<Order> orderPage = orderRepository.findById(orderId, PageRequest.of(page, size, Sort.by(sortedBy)));

        return orderPage.map(this::fromOrder);
    }

    @Override

    public Set<OrderDTO> getAllOrder() {
        Set<OrderDTO> orderDTOS = new HashSet<>();
        Iterable<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderState(order.getOrderState());
            orderDTO.setCreatedDate(order.getCreatedDate());
            orderDTO.setOrderItems(fromOrderItem(order.getOrderItems()));

            orderDTOS.add(orderDTO);
        }

        return orderDTOS;
    }

    private Set<OrderItemDTO> fromOrderItem(Set<OrderItem> orderItems) {
        Set<OrderItemDTO> orderItemDTOS = new HashSet<>();

        for (OrderItem orderItem : orderItems) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setOrderPrice(orderItem.getOrderPrice());
            orderItemDTO.setQuantity(orderItem.getQuantity());
            orderItemDTO.setProductColor(orderItem.getProduct().getColor());
            orderItemDTO.setProductName(orderItem.getProduct().getName());
            orderItemDTO.setProductId(orderItem.getProduct().getId().intValue());

            orderItemDTOS.add(orderItemDTO);
        }

        return orderItemDTOS;
    }

    private OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderState(order.getOrderState());
        orderDTO.setCreatedDate(order.getCreatedDate());
        orderDTO.setOrderItems(fromOrderItem(order.getOrderItems()));

        return orderDTO;
    }


}
