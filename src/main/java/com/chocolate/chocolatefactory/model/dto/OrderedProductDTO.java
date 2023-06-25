package com.chocolate.chocolatefactory.model.dto;

import com.chocolate.chocolatefactory.model.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderedProductDTO {
    
    private Long id;
    private Order order;
    private ProductDTO productDTO;
    private Integer quantity;

    private String productName;


    public OrderedProductDTO(){

    }

    public OrderedProductDTO(Long id, Order order, ProductDTO productDTO, Integer quantity){
        this.id = id;
        this.order = order;
        this.productDTO = productDTO;
        this.quantity = quantity;
    }

    public OrderedProductDTO(Order order, ProductDTO productDTO, Integer quantity){
        this.order = order;
        this.productDTO = productDTO;
        this.quantity = quantity;
    }
}
