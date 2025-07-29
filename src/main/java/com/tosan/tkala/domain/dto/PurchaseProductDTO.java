package com.tosan.tkala.domain.dto;

import lombok.Data;

@Data
public class PurchaseProductDTO {

    private Long productId;
    private Integer productCount;
    private Long userId;
}
