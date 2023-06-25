package com.chocolate.chocolatefactory.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverCategoryDTO {

    private Long id;
    private String categoryMark;
    private Float maxLoadCapacity;
    private List<DriverDTO> driversDTO;

    public DriverCategoryDTO(Long id, String categoryMark, Float maxLoadCapacity){
        this.id = id;
        this.categoryMark = categoryMark;
        this.maxLoadCapacity = maxLoadCapacity;
    }
    
}
