package com.chocolate.chocolatefactory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolate.chocolatefactory.model.Truck;
import com.chocolate.chocolatefactory.model.dto.TruckDTO;
import com.chocolate.chocolatefactory.service.TruckService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/trucks")
public class TruckController {
    
    private final TruckService truckService;

    @Autowired
    public TruckController(TruckService truckService){
        this.truckService = truckService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<TruckDTO> findOneById(@PathVariable Long id){
        Truck truck = truckService.findById(id);

        if(truck == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TruckDTO truckDTO = new TruckDTO(truck.getId(), truck.getRegistrationNumber(), truck.getName(), truck.getCapacity(), truck.getDriveability());
        return new ResponseEntity<>(truckDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TruckDTO>> findAll(){
        List<Truck> trucks = truckService.findAll();
        List<TruckDTO> trucksDTO = new ArrayList<TruckDTO>();

        for(Truck truck : trucks){
            TruckDTO truckDTO = new TruckDTO(truck.getId(), truck.getRegistrationNumber(), truck.getName(), truck.getCapacity(), truck.getDriveability());
            trucksDTO.add(truckDTO);
        }
        return new ResponseEntity<>(trucksDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TruckDTO> create(@RequestBody TruckDTO truckDTO) throws Exception{
        return new ResponseEntity<>(truckService.create(truckDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<TruckDTO> update(@PathVariable Long id, @RequestBody TruckDTO truckDTO) throws Exception{
        if(truckService.findById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(truckService.update(truckDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(truckService.delete(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findAvailable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TruckDTO>> findAvailableTrucksForEngagement(@PathVariable Long id){
        List<Truck> trucks = truckService.getAvailableTrucksByDriverCategory(id);
        List<TruckDTO> trucksDTO = new ArrayList<TruckDTO>();

        for(Truck truck : trucks){
            TruckDTO truckDTO = new TruckDTO(truck.getId(), truck.getRegistrationNumber(), truck.getName(), truck.getCapacity(), truck.getDriveability());
            trucksDTO.add(truckDTO);
        }
        return new ResponseEntity<>(trucksDTO, HttpStatus.OK);
    }
}
