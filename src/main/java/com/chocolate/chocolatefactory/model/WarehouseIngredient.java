package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "warehouse_ingredients")
public class WarehouseIngredient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column
    private Integer quantity;

    public WarehouseIngredient(Long id, Warehouse warehouse, Ingredient ingredient, Integer quantity) {
        this.id = id;
        this.warehouse = warehouse;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public WarehouseIngredient() {
    }
}
