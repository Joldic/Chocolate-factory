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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolate.chocolatefactory.model.DriverCategory;
import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.dto.DriverCategoryDTO;
import com.chocolate.chocolatefactory.model.dto.DriverDTO;
import com.chocolate.chocolatefactory.model.dto.EngagementDTO;
import com.chocolate.chocolatefactory.model.dto.TruckDTO;
import com.chocolate.chocolatefactory.service.EngagementService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/engagements")
public class EngagementController {

    private final EngagementService engagementService;

    @Autowired
    public EngagementController(EngagementService engagementService){
        this.engagementService = engagementService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<EngagementDTO>> getActiveEngagements(){
        List<Engagement> engagements = engagementService.getEngagements();

        List<EngagementDTO> engagementsDTO = new ArrayList<>();

        for(Engagement engagement : engagements){

            TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());
            DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());

            EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO, engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
            engagementsDTO.add(engagementDTO);
        }

        return new ResponseEntity<>(engagementsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<EngagementDTO> create(@RequestBody EngagementDTO engagementDTO){
        
        Engagement engagement = engagementService.create(engagementDTO);
        if(engagement!=null){
            TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());
            DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());

            EngagementDTO newEngagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO, engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
            return new ResponseEntity<>(newEngagementDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
    }
    
    @GetMapping(value = "/currentByDriver/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<EngagementDTO> findCurrentByDriver(@PathVariable Long id){
        
        Engagement engagement = engagementService.findCurrentEngagementByDriver(id);
        if(engagement == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
        TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());
        DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());

        EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO, engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
        return new ResponseEntity<EngagementDTO>(engagementDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/currentByTruck/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<EngagementDTO> findCurrentByTruck(@PathVariable Long id){

        Engagement engagement = engagementService.findCurrentEngagementByTruck(id);
        if(engagement == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());
        DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());

        EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO, engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
        return new ResponseEntity<EngagementDTO>(engagementDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<EngagementDTO>> getAllEngagements(){
        List<Engagement> engagements = engagementService.findAll();

        List<EngagementDTO> engagementsDTO = new ArrayList<>();

        for(Engagement engagement : engagements){

            TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

            List<DriverCategoryDTO> categoriesDTO = new ArrayList<>();
            for(DriverCategory category : engagement.getDriver().getCategories()){
                DriverCategoryDTO categoryDTO = new DriverCategoryDTO(category.getId(), category.getCategoryMark(), category.getMaxLoadCapacity());
                categoriesDTO.add(categoryDTO);
            }

            DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg(), categoriesDTO);

            EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO, engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
            engagementsDTO.add(engagementDTO);
        }

        return new ResponseEntity<>(engagementsDTO, HttpStatus.OK);
    }
}
