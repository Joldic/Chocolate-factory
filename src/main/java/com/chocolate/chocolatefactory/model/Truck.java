package com.chocolate.chocolatefactory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "trucks")
public class Truck implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String RegistrationNumber;

    @Column
    private String Name;

    @Column
    private Float Capacity;

    @Column
    private Boolean Driveability;

    @Column
    private Boolean Deleted = Boolean.FALSE;


    @OneToMany(mappedBy = "truck")
    private List<Engagement> engagements;

    public Truck(){

    }

    public Truck(Long id, String registrationNumber, String name, Float capacity, Boolean driveability, Boolean deleted){
        this.Id=id;
        this.RegistrationNumber=registrationNumber;
        this.Name=name;
        this.Capacity=capacity;
        this.Driveability=driveability;
        this.Deleted = deleted;
    }

    public Truck(String registrationNumber, String name, Float capacity, Boolean driveability, Boolean deleted){
        this.RegistrationNumber=registrationNumber;
        this.Name=name;
        this.Capacity=capacity;
        this.Driveability=driveability;
        this.Deleted=deleted;
    }

}