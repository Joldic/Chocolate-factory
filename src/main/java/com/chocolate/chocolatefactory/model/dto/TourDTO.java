package com.chocolate.chocolatefactory.model.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TourDTO {
    private Long id;
    private String city;
    private LocalDate date;
    private List<OrderDTO> ordersDTO;
    private List<EngagementDTO> engagementsDTO;

    public TourDTO(){

    }

    public TourDTO(Long id, String city, LocalDate date, List<OrderDTO> ordersDTO, List<EngagementDTO> engagementsDTO){
        this.id = id;
        this.city = city;
        this.date = date;
        this.ordersDTO = ordersDTO;
        this.engagementsDTO = engagementsDTO;
    }

    public TourDTO(String city, LocalDate date, List<OrderDTO> ordersDTO, List<EngagementDTO> engagementsDTO){
        this.city = city;
        this.date = date;
        this.ordersDTO = ordersDTO;
        this.engagementsDTO = engagementsDTO;
    }
}
