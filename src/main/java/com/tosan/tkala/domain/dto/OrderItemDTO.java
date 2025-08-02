package com.tosan.tkala.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {

    @JsonIgnore
    private Integer productId;
    private String productName;
    private String productColor;
    private Integer quantity;
    private BigDecimal orderPrice;

}
