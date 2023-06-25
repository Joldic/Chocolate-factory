package com.chocolate.chocolatefactory.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ordered_product")
public class OrderedProduct implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column 
    private  Integer quantity;

    public OrderedProduct(){

    }

    public OrderedProduct(Order order, Product product, Integer quantity){
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
