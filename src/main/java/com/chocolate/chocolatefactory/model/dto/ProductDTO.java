package com.chocolate.chocolatefactory.model.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {
    
    private Long Id;
    private String productName;
    private Double price;
    private Float weight;

    private List<IngredientDTO> ingredientDTOList;

    public ProductDTO(){

    }

    public ProductDTO(Long id, String productName, Double price, Float weight){
        this.Id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
    }
}
