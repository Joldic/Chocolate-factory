package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "ingredient")
    private List<ContractPart> contractParts;

    @OneToMany(mappedBy = "ingredient")
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "ingredient")
    private List<CompanyStock> companyStocks;

    @OneToMany(mappedBy = "ingredient")
    private List<WarehouseIngredient> warehouseIngredientList;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientRequest> ingredientRequests;

    public Ingredient() {
    }

    public Ingredient(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ingredient(String name) {
        this.name = name;
    }
}
