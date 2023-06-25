package com.chocolate.chocolatefactory.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "engagements")
public class Engagement implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column
    private LocalDate engagementStartDate;

    @Column
    private LocalDate engagementEndDate;

    @ManyToMany
    @JoinTable(name = "tourEngagements",
                joinColumns = @JoinColumn(name = "engagement_id"),
                inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<Tour> tours;

    public Engagement(){

    }

    public Engagement(Long id, Truck truck, Driver driver, List<Tour> tours){
        this.id = id;
        this.truck = truck;
        this.driver = driver;
        this.tours = tours;
    }

    public Engagement(Long id, Truck truck, Driver driver, List<Tour> tours, LocalDate startDate, LocalDate endDate){
        this.id = id;
        this.truck = truck;
        this.driver = driver;
        this.tours = tours;
        this.engagementStartDate = startDate;
        this.engagementEndDate = endDate;
    }

    public Engagement(Truck truck, Driver driver, List<Tour> tours, LocalDate startDate, LocalDate endDate){
        this.truck = truck;
        this.driver = driver;
        this.tours = tours;
        this.engagementStartDate = startDate;
        this.engagementEndDate = endDate;
    }
}
