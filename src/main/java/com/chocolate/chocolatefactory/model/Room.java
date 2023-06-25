package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "rooms")
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "room")
    private List<Machine> machineList;

    public Room(){

    }

    public Room(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
