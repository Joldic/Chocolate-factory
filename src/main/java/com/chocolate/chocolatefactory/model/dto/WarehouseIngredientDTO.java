package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseIngredientDTO {
    private Long id;
    private String ingredientName;
    private Long ingredientId;
    private Integer quantity;
    private Boolean status;

    public WarehouseIngredientDTO(){

    }

    public WarehouseIngredientDTO(Long id, String ingredientName, Long ingredientId, Integer quantity) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    public WarehouseIngredientDTO(String ingredientName, Integer quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }
}
