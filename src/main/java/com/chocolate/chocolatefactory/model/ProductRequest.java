package com.chocolate.chocolatefactory.model;

import com.chocolate.chocolatefactory.model.Product;
import com.chocolate.chocolatefactory.model.dto.ProductDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_requests")
public class ProductRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @Column
    private Integer requestFlag;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public  ProductRequest(){

    }

    public ProductRequest(Long id, Integer quantity, Integer requestFlag) {
        this.id = id;
        this.quantity = quantity;
        this.requestFlag = requestFlag;
    }
}
