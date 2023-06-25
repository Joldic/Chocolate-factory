package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;

    public UserDTO () {}

    public UserDTO(String firstName, String lastName, String email, String password, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public UserDTO(String firstName, String lastName, String email, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
    }
}
