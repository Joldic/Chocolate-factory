package com.chocolate.chocolatefactory.model.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EngagementDTO {
    private Long id;
    private TruckDTO truckDTO;
    private DriverDTO driverDTO;
    private LocalDate startDate;
    private LocalDate endDate;

    public EngagementDTO(){

    }

    public EngagementDTO(Long id, TruckDTO truckDTO, DriverDTO driverDTO){
        this.id = id;
        this.driverDTO = driverDTO;
        this.truckDTO = truckDTO;
    }

    public EngagementDTO(Long id, TruckDTO truckDTO, DriverDTO driverDTO, LocalDate staretDate, LocalDate endDate){
        this.id = id;
        this.driverDTO = driverDTO;
        this.truckDTO = truckDTO;
        this.startDate=staretDate;
        this.endDate=endDate;
    }
}
