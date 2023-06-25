package com.chocolate.chocolatefactory.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO{
    private Long Id;
    private String FirstName;
    private String LastName;
    private String Adress;
    private String PhoneNumber;
    private String Jmbg;
    private List<DriverCategoryDTO> categoriesDTO;
    private String username;
    private String email;

    public DriverDTO(){

    }

    public DriverDTO(Long id, String firstName, String lastName, String adress, String phoneNumber, String jmbg, List<DriverCategoryDTO> categoriesDTO){
        this.Id=id;
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
        this.categoriesDTO = categoriesDTO;
    }

    public DriverDTO(Long id, String firstName, String lastName, String adress, String phoneNumber, String jmbg){
        this.Id=id;
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
    }

    public DriverDTO(Long id, String firstName, String lastName, String adress, String phoneNumber, String jmbg, List<DriverCategoryDTO> categoriesDTO, String username, String email){
        this.Id=id;
        this.FirstName=firstName;
        this.LastName=lastName;
        this.Adress=adress;
        this.PhoneNumber=phoneNumber;
        this.Jmbg=jmbg;
        this.categoriesDTO = categoriesDTO;
        this.username = username;
        this.email = email;
    }
}