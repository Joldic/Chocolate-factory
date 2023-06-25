package com.chocolate.chocolatefactory.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private LocalDate date;

    @Column
    private Double totalPrice;

    @Column
    private Float totalWeight;

    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus status = OrderStatus.Pending;

    //Prioritet je za distribuciju da li kasni ili ne, prilikom kreiranja i proizvodnje je false
    @Column
    private Boolean priority = false;

    //Kupac koji je kreirao porudzbinu
    @ManyToOne
    @JoinColumn(name = "regular_user_id")
    private User user;    

    //U kojoj se turi salje, default je null, dok ne bude gotova i spremna za distribuciju
    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour = null;

    //Proizvodi koji cine porudzbinu, mora posebna klasa jer porudzbinu moze da cini vise proizvoda i svaki u razlicitoj kolicini
    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProducts;

    public Order(){

    }

    public Order(Long id, String address, String city, LocalDate date, Double totalPrice, Float totalWeight, OrderStatus status, Boolean priority, User user, Tour tour, List<OrderedProduct> orderedProducts){
        this.Id = id;
        this.address = address;
        this.city = city;
        this.date = date;
        this.status = status;
        this.priority = priority;
        this.user = user;
        this.tour = tour;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.orderedProducts = orderedProducts;
    }
    

}
