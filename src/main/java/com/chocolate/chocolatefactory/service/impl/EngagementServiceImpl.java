package com.chocolate.chocolatefactory.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.Driver;
import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.Tour;
import com.chocolate.chocolatefactory.model.Truck;
import com.chocolate.chocolatefactory.model.dto.EngagementDTO;
import com.chocolate.chocolatefactory.repository.EngagementRepository;
import com.chocolate.chocolatefactory.service.EngagementService;

@Service
public class EngagementServiceImpl implements EngagementService{
    
    private final EngagementRepository engagementRepository;

    @Autowired
    public EngagementServiceImpl(EngagementRepository engagementRepository){
        this.engagementRepository = engagementRepository;
    }

    @Override
    public List<Engagement> findAll(){
        return engagementRepository.findAll();
    }
    
    @Override
    public List<Engagement> getEngagements(){
        List<Engagement> activeEngagements = new ArrayList<>();

        for(Engagement engagement : findAll()){
            if(!engagement.getTruck().getDeleted() && !engagement.getDriver().getDeleted()){
                activeEngagements.add(engagement);
            }
        }
        return activeEngagements;
    }

    @Override
    public List<Engagement> findByDriver(Long id){
        List<Engagement> engagements = new ArrayList<>();

        for(Engagement engagement : findAll()){
            if(engagement.getDriver().getId() == id){
                engagements.add(engagement);
            }
        }
        return engagements;
    }

    @Override
    public List<Engagement> findByTruck(Long id){
        List<Engagement> engagements = new ArrayList<>();

        for(Engagement engagement : findAll()){
            if(engagement.getTruck().getId() == id){
                engagements.add(engagement);
            }
        }
        return engagements;
    }

    @Override
    public Engagement create(EngagementDTO engagementDTO){
        
        if(isDriverFreeForEngagement(engagementDTO.getDriverDTO().getId()) && isTruckFreeForEngagement(engagementDTO.getTruckDTO().getId())){

            Driver driver = new Driver(engagementDTO.getDriverDTO().getId(), engagementDTO.getDriverDTO().getFirstName(), engagementDTO.getDriverDTO().getLastName(), engagementDTO.getDriverDTO().getAdress(), engagementDTO.getDriverDTO().getPhoneNumber(), engagementDTO.getDriverDTO().getJmbg(), false);
            Truck truck = new Truck(engagementDTO.getTruckDTO().getId(), engagementDTO.getTruckDTO().getRegistrationNumber(), engagementDTO.getTruckDTO().getName(), engagementDTO.getTruckDTO().getCapacity(), engagementDTO.getTruckDTO().getDriveability(), false);

            LocalDate startDate = LocalDate.now();
            Engagement engagement = new Engagement(truck, driver, null, startDate, null);
            
            return engagementRepository.save(engagement);    
        }
        return null;
        
    }

    @Override
    public Boolean isDriverFreeForEngagement(Long id){

        if(findByDriver(id)==null)
            return true;

        for(Engagement engagement : findByDriver(id)){
            if(!engagement.getTruck().getDeleted()){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean isTruckFreeForEngagement(Long id){

        if(findByTruck(id)==null)
            return true;

        for(Engagement engagement : findByTruck(id)){
            if(!engagement.getDriver().getDeleted()){
                return false;
            }
        }
        return true;
    }

    @Override
    public Engagement findCurrentEngagementByDriver(Long id){
        for(Engagement engagement : findByDriver(id)){
            if(!engagement.getTruck().getDeleted()){
                return engagement;
            }
        }
        return null;
    }
    
    @Override
    public Engagement findCurrentEngagementByTruck(Long id){
        for(Engagement engagement : findByTruck(id)){
            if(!engagement.getDriver().getDeleted()){
                return engagement;
            }
        }
        return null;
    }

    @Override
    public Boolean setEngagementEndDate(Long id){
        for(Engagement engagement : findAll()){
            if(engagement.getId() == id){
                Engagement engagementToUpdate = new Engagement(engagement.getId(), engagement.getTruck(), engagement.getDriver(), engagement.getTours(), engagement.getEngagementStartDate(), LocalDate.now());
                Engagement updatedEngagement = engagementRepository.save(engagementToUpdate);
                if(updatedEngagement != null){
                    return true;
                }
                return false;
            }
        }
        return false;
        
    }

    @Override
    public Engagement findOne(Long id){
        for(Engagement engagement : findAll()){
            if(engagement.getId() == id)
                return engagement;
        }
        return null;
    }

    @Override
    public Boolean setTour(Long id, Tour tour){
        Engagement engagement = findOne(id);
        List<Tour> tours = engagement.getTours();
        tours.add(tour);

        engagement.setTours(tours);
        Engagement engagementToUpdate = new Engagement(engagement.getId(), engagement.getTruck(), engagement.getDriver(), engagement.getTours(), engagement.getEngagementStartDate(), engagement.getEngagementEndDate());
        Engagement updatedEngagement = engagementRepository.save(engagementToUpdate);
            if(updatedEngagement != null){
                return true;
            }
        return false;
    }

}
