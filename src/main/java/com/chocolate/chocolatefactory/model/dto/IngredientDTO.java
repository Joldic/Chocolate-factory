package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientDTO {

    private Long id;
    private String name;
    private Integer quantity;

    public IngredientDTO(){

    }

    public IngredientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
