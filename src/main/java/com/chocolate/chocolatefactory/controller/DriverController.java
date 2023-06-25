package com.chocolate.chocolatefactory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolate.chocolatefactory.model.Driver;
import com.chocolate.chocolatefactory.model.DriverCategory;
import com.chocolate.chocolatefactory.model.dto.DriverCategoryDTO;
import com.chocolate.chocolatefactory.model.dto.DriverDTO;
import com.chocolate.chocolatefactory.service.DriverService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {
    
    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService){
        this.driverService=driverService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<DriverDTO> findOneById(@PathVariable Long id){
       Driver driver=driverService.findById(id);

        if(driver==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<DriverCategoryDTO> categoriesDTO = new ArrayList<>();
        for(DriverCategory category : driver.getCategories()){
            DriverCategoryDTO categoryDTO = new DriverCategoryDTO(category.getId(), category.getCategoryMark(), category.getMaxLoadCapacity());
            categoriesDTO.add(categoryDTO);
        }

        DriverDTO driverDTO = new DriverDTO(driver.getId(), driver.getFirstName(), driver.getLastName(), driver.getAdress(), driver.getPhoneNumber(), driver.getJmbg(), categoriesDTO, driver.getUsername(), driver.getEmail());
        return new ResponseEntity<>(driverDTO, HttpStatus.OK);

    }

    @GetMapping(value ="/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<DriverDTO>> findAll() {
        List<Driver> drivers = driverService.findAll();
        List<DriverDTO> driversDTO = new ArrayList<DriverDTO>();

        for(Driver driver : drivers){
            DriverDTO driverDTO = new DriverDTO(driver.getId(), driver.getFirstName(), driver.getLastName(), driver.getAdress(), driver.getPhoneNumber(), driver.getJmbg());
            driversDTO.add(driverDTO);
        }

        return new ResponseEntity<>(driversDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<DriverDTO> create(@RequestBody DriverDTO driverDTO) throws Exception{
        return new ResponseEntity<>(driverService.create(driverDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<DriverDTO> update(@PathVariable Long id, @RequestBody DriverDTO driverDTO)throws Exception{
        if(driverService.findById(id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(driverService.update(driverDTO, id), HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        
        if(driverService.delete(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value ="/findAvailable", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<DriverDTO>> findAvailableDriversForEngagement(){
        List<Driver> drivers = driverService.getDriversAvailableForEngagement();
        List<DriverDTO> driversDTO = new ArrayList<DriverDTO>();

        for(Driver driver : drivers){

            List<DriverCategoryDTO> categoriesDTO = new ArrayList<>();
            for(DriverCategory category : driver.getCategories()){
                DriverCategoryDTO categoryDTO = new DriverCategoryDTO(category.getId(), category.getCategoryMark(), category.getMaxLoadCapacity());
                categoriesDTO.add(categoryDTO);
            }

            DriverDTO driverDTO = new DriverDTO(driver.getId(), driver.getFirstName(), driver.getLastName(), driver.getAdress(), driver.getPhoneNumber(), driver.getJmbg(), categoriesDTO);
            driversDTO.add(driverDTO);
        }

        return new ResponseEntity<>(driversDTO, HttpStatus.OK);
    }

}
