package com.chocolate.chocolatefactory.model;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "driver_category")
public class DriverCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String categoryMark;

    @Column
    private Float maxLoadCapacity;

    @ManyToMany
    @JoinTable(name = "driverCategories",
                joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private List<Driver> drivers;

    public DriverCategory(){

    }
    public DriverCategory(Long id, String categoryMark, Float maxLoadCapacity){
        this.id = id;
        this.categoryMark = categoryMark;
        this.maxLoadCapacity = maxLoadCapacity;
    }
}
