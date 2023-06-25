package com.chocolate.chocolatefactory.service;

import java.util.List;

import com.chocolate.chocolatefactory.model.Truck;
import com.chocolate.chocolatefactory.model.dto.TruckDTO;

public interface TruckService {
    
    Truck findById(Long id);

    List<Truck> findAll();

    TruckDTO create(TruckDTO truckDTO) throws Exception;

    TruckDTO update(TruckDTO truckDTO, Long id) throws Exception;

    Boolean delete(Long id);

    List<Truck> getAvailableTrucksByDriverCategory(Long id);

}
