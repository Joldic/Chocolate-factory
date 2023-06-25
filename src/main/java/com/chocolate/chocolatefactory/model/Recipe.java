package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "recipes")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @Column
    private Integer quantity;

    public Recipe(){

    }

    public Recipe(Long id, Product product, Ingredient ingredient, Integer quantity) {
        this.id = id;
        this.product = product;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Recipe(Product product, Ingredient ingredient, Integer quantity) {
        this.product = product;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}
