package com.chocolate.chocolatefactory.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tours")
public class Tour implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String City;

    @Column
    private LocalDate Date;

    @ManyToMany(mappedBy = "tours")
    private List<Engagement> engagements;

    @OneToMany(mappedBy = "tour")
    private List<Order> Orders;

    public Tour(){

    }
     
    public Tour(Long id, String city, LocalDate date, List<Engagement> engagements, List<Order> orders){
        this.Id = id;
        this.City = city;
        this.Date = date;
        this.engagements = engagements;
        this.Orders = orders;

    }

    public Tour(String city, LocalDate date, List<Engagement> engagements, List<Order> orders){
        this.City = city;
        this.Date = date;
        this.engagements = engagements;
        this.Orders = orders;
    }

}
