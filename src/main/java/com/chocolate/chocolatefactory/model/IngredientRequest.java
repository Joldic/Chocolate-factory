package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "ingredient_request")
public class IngredientRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer quantity;

    @Column
    private Integer requestFlag;

    @Column
    private Date creationDate;

    @Column
    private Date deliveryDeadlineDate;

    @ManyToOne
    private Ingredient ingredient;

    public IngredientRequest(){

    }

    public IngredientRequest(Long id, Integer quantity, Integer requestFlag, Date creationDate, Date deliveryDeadlineDate) {
        this.id = id;
        this.quantity = quantity;
        this.requestFlag = requestFlag;
        this.creationDate = creationDate;
        this.deliveryDeadlineDate = deliveryDeadlineDate;
    }

    public IngredientRequest(Integer quantity, Integer requestFlag, Date creationDate, Date deliveryDeadlineDate) {
        this.quantity = quantity;
        this.requestFlag = requestFlag;
        this.creationDate = creationDate;
        this.deliveryDeadlineDate = deliveryDeadlineDate;
    }
}
