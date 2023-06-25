package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.Driver;
import com.chocolate.chocolatefactory.model.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    
    Driver findById(Long id);

    List<Driver> findAll();

    DriverDTO create(DriverDTO driverDTO) throws Exception;

    DriverDTO update(DriverDTO driverDTO, Long id) throws Exception;

    Boolean delete(Long id);

    List<Driver> getDriversAvailableForEngagement();

}
