package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class IngredientRequestDTO {

    private Long id;
    private Integer quantity;
    private Integer requestFlag;
    private String creationDate;
    private String deliveryDeadlineDate;
    private IngredientDTO ingredientDTO;

    public IngredientRequestDTO() {
    }

    public IngredientRequestDTO(Long id, Integer quantity, Integer requestFlag, String creationDate, String deliveryDeadlineDate) {
        this.id = id;
        this.quantity = quantity;
        this.requestFlag = requestFlag;
        this.creationDate = creationDate;
        this.deliveryDeadlineDate = deliveryDeadlineDate;
    }
}
