package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CompanyStockDTO {

    private Long id;
    private Double price;
    private Integer quantity;
    private String expiringDate;
    private String ingredientName;

    public CompanyStockDTO() {
    }

    public CompanyStockDTO(Long id, Double price, Integer quantity, String expiringDate) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.expiringDate = expiringDate;
    }

    public CompanyStockDTO(Double price, Integer quantity, String expiringDate) {
        this.price = price;
        this.quantity = quantity;
        this.expiringDate = expiringDate;
    }
}
