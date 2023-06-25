package com.chocolate.chocolatefactory.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "drivers")
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String FirstName;

    @Column
    private String LastName;

    @Column
    private String Adress;

    @Column
    private String PhoneNumber;

    @Column(unique = true)
    private String Jmbg;

    @Column(unique = true)
    private String username;

    @Column
    private String email;

    @Column
    private Boolean Deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "drivers")
    private List<DriverCategory> categories;

    @OneToMany(mappedBy = "driver")
    private List<Engagement> engagements;

    public Driver(){

    }

    public Driver(Long id, String firstName, String lastName, String adress, String phoneNumber, String jmbg, Boolean deleted){
        this.Id=id;
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
        this.Deleted = deleted;
    }

    public Driver(String firstName, String lastName, String adress, String phoneNumber, String jmbg, Boolean deleted, List<DriverCategory> categories){
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
        this.Deleted = deleted;
        this.categories = categories;
    }

    public Driver(Long id, String firstName, String lastName, String adress, String phoneNumber, String jmbg, Boolean deleted, List<DriverCategory> categories, String username, String email){
        this.Id = id;
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
        this.Deleted = deleted;
        this.categories = categories;
        this.username = username;
        this.email = email;
    }

    public Driver(String firstName, String lastName, String adress, String phoneNumber, String jmbg, Boolean deleted, List<DriverCategory> categories, String username, String email){
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
        this.Deleted = deleted;
        this.categories = categories;
        this.username = username;
        this.email = email;
    }

}
