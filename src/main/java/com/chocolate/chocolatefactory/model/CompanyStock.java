package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "company_stock")
public class CompanyStock implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price;

    @Column
    private Integer quantity;

    @Column
    private Date expiringDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public CompanyStock() {
    }

    public CompanyStock(Long id, Double price, Integer quantity, Date expiringDate) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.expiringDate = expiringDate;
    }
}
