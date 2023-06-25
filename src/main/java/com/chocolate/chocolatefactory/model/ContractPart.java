package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "contract_part")
public class ContractPart implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    public ContractPart() {
    }

    public ContractPart(Long id, Double price) {
        this.id = id;
        this.price = price;
    }

    public ContractPart(Double price) {
        this.price = price;
    }
}
