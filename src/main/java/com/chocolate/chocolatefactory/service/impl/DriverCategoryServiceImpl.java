package com.chocolate.chocolatefactory.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.DriverCategory;
import com.chocolate.chocolatefactory.repository.DriverCategoryRepository;
import com.chocolate.chocolatefactory.service.DriverCategoryService;

@Service
public class DriverCategoryServiceImpl implements DriverCategoryService {
    
    private final DriverCategoryRepository driverCategoryRepository;

    @Autowired
    public DriverCategoryServiceImpl(DriverCategoryRepository driverCategoryRepository){
        this.driverCategoryRepository = driverCategoryRepository;
    }

    @Override
    public List<DriverCategory> findAll(){
        return driverCategoryRepository.findAll();
    }
}
