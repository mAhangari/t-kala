package com.tosan.tkala.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductDTO {

    private String name;
    private String color;
    private Integer productQuantity;
    private Set<String> availableStores;
    private Integer orderedQuantity;

}
