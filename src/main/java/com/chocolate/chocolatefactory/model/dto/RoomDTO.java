package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDTO {
    private Long id;

    private String name;

    public RoomDTO(){

    }

    public RoomDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
