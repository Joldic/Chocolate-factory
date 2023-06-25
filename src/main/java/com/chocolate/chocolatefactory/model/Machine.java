package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name= "machines")
public class Machine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer inputQuantity;

    @Enumerated(EnumType.STRING)
    @Column
    private Status state;

    @Enumerated(EnumType.STRING)
    @Column
    private MachineType type;

    @Column
    private Integer workingDays;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room = null;

    public Machine(){

    }
    public Machine(Long id, String name, Integer inputQuantity, Status state, Integer workingDays) {
        this.id = id;
        this.name = name;
        this.inputQuantity = inputQuantity;
        this.state = state;
        this.workingDays = workingDays;
    }

    public Machine( String name, Integer inputQuantity, Status state, Integer workingDays) {
        this.name = name;
        this.inputQuantity = inputQuantity;
        this.state = state;
        this.workingDays = workingDays;
    }


}
