package com.chocolate.chocolatefactory.model.dto;

import com.chocolate.chocolatefactory.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachineDTO {
    private Long id;

    private String name;

    private Integer inputQuantity;

    private String state;

    private String type;

    private Integer workingDays;

    public MachineDTO(){

    }

    public MachineDTO(Long id, String name, Integer inputQuantity, String state, Integer workingDays) {
        this.id = id;
        this.name = name;
        this.inputQuantity = inputQuantity;
        this.state = String.valueOf(state);
        this.workingDays = workingDays;
    }

    public MachineDTO( String name, Integer inputQuantity, String state, Integer workingDays) {
        this.name = name;
        this.inputQuantity = inputQuantity;
        this.state = String.valueOf(state);
        this.workingDays = workingDays;
    }
}
