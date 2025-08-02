package com.tosan.tkala.domain.dto;

import com.tosan.tkala.domain.OrderItem;
import com.tosan.tkala.domain.enumuration.OrderState;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDTO {

    private OrderState orderState;
    private LocalDateTime createdDate;
    private Set<OrderItemDTO> orderItems = new HashSet<>();

}
