package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractPartDTO {

    private Long id;
    private Double price;
    private String ingredientName;

    public ContractPartDTO() {
    }

    public ContractPartDTO(Long id, Double price, String ingredientName) {
        this.id = id;
        this.price = price;
        this.ingredientName = ingredientName;
    }

    public ContractPartDTO(Double price, String ingredientName) {
        this.price = price;
        this.ingredientName = ingredientName;
    }
}
