package com.chocolate.chocolatefactory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String productName;

    @Column
    private Double price;

    @Column
    private Float weight;

    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> orderedProcudts;

    @OneToMany(mappedBy = "product")
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "product")
    List<ProductRequest> productRequests;

    @ManyToMany
    @JoinTable(name = "products_ingredients",
               joinColumns = @JoinColumn(name = "product_id"),
                inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredientList;

    public Product() {
    }

    public Product(Long id, String productName, Double price, Float weight) {
        this.Id = id;
        this.productName = productName;
        this.price = price;
        this.weight = weight;
    }

    public Product (String productName, Double price, Float weight) {
        this.productName = productName;
        this.price = price;
        this.weight = weight;
    }
}

   