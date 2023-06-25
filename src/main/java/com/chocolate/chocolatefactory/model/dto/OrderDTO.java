package com.chocolate.chocolatefactory.model.dto;

import java.time.LocalDate;
import java.util.List;

import com.chocolate.chocolatefactory.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private String address;
    private String city;
    private LocalDate date;
    private Double totalPrice;
    private Float totalWeight;
    private OrderStatus status;
    private Boolean priority;
    private UserDTO userDTO;
    private TourDTO tourDTO = null;
    private List<OrderedProductDTO> orderedProductsDTO;
    private String deliveryDate;

    public OrderDTO(){

    }

    public OrderDTO(Long id, String address, String city, LocalDate date, Double totalPrice, Float totalWeight, OrderStatus status, Boolean priority, UserDTO userDTO, List<OrderedProductDTO> orderedProductsDTO){
        this.id = id;
        this.address = address;
        this.city = city;
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.status = status;
        this.priority = priority;
        this.userDTO = userDTO;
        this.orderedProductsDTO = orderedProductsDTO;
    }

    public OrderDTO(Long id, String address, String city, LocalDate date, Double totalPrice, Float totalWeight, OrderStatus status, Boolean priority, UserDTO userDTO, TourDTO tourDTO, List<OrderedProductDTO> orderedProductsDTO){
        this.id = id;
        this.address = address;
        this.city = city;
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalWeight = totalWeight;
        this.status = status;
        this.priority = priority;
        this.userDTO = userDTO;
        this.tourDTO = tourDTO;
        this.orderedProductsDTO = orderedProductsDTO;
    }
}
