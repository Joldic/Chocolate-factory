package com.chocolate.chocolatefactory.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {

    private Long id;
    private String name;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;
    private String email;

    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String name, String country, String city, String address, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public CompanyDTO(String name, String country, String city, String address, String phoneNumber, String email) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
