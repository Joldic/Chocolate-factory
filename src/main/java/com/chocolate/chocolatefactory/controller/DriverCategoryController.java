package com.chocolate.chocolatefactory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolate.chocolatefactory.model.DriverCategory;
import com.chocolate.chocolatefactory.model.dto.DriverCategoryDTO;
import com.chocolate.chocolatefactory.service.DriverCategoryService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/driverCategory")
public class DriverCategoryController {
    
    private final DriverCategoryService driverCategoryService;

    @Autowired
    public DriverCategoryController(DriverCategoryService driverCategoryService){
        this.driverCategoryService = driverCategoryService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<DriverCategoryDTO>> findAll(){
        List<DriverCategoryDTO> categoriesDTO = new ArrayList<>();

        for(DriverCategory driverCategory : driverCategoryService.findAll()){
            DriverCategoryDTO categoryDTO = new DriverCategoryDTO(driverCategory.getId(), driverCategory.getCategoryMark(), driverCategory.getMaxLoadCapacity());
            categoriesDTO.add(categoryDTO);
        }
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }
}
