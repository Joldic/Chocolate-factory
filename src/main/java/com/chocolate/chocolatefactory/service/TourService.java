package com.chocolate.chocolatefactory.service;

import java.time.LocalDate;
import java.util.List;

import org.javatuples.Pair;

import com.chocolate.chocolatefactory.model.Tour;
import com.chocolate.chocolatefactory.model.dto.TourDTO;

public interface TourService {
    
    Tour findById(Long id);

    List<Tour> findAll();

    List<Tour> createTransportationPlan();

    List<Pair<Float, Tour>> calculateTourWeight(List<Tour> tours);

    List<Tour> acceptTransportationPlan(List<TourDTO> toursDTO);

    List<Tour> getTransportationPlan(LocalDate date);

    List<Tour> getTransportationPlanForDriver(LocalDate date, String driverUsername);

    List<Tour> getToursByMonth(String month);

    List<Tour> getToursByDriver(Long driverId, String month);

}
