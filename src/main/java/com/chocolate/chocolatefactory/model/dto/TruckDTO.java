package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TruckDTO {
    private Long Id;
    private String RegistrationNumber;
    private String Name;
    private Float Capacity;
    private Boolean Driveability;

    public TruckDTO(){

    }

    public TruckDTO(Long id, String registrationNumber, String name, Float capacity, Boolean driveability){
        this.Id=id;
        this.RegistrationNumber=registrationNumber;
        this.Name=name;
        this.Capacity=capacity;
        this.Driveability=driveability;
    }

}
