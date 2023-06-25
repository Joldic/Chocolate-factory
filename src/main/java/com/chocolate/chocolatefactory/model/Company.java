package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "companies")
public class Company implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "company")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "company")
    private List<CompanyStock> companyStocks;

    public Company() {
    }

    public Company(Long id, String name, String country, String city, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
