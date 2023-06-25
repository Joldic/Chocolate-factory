package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "warehouses")
public class Warehouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "warehouse")
    private List<WarehouseIngredient> warehouseIngredientList;

    public Warehouse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Warehouse() {
    }
}
