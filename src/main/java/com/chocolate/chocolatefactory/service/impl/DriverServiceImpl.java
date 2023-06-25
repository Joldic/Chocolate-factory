package com.chocolate.chocolatefactory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.Driver;
import com.chocolate.chocolatefactory.model.DriverCategory;
import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.dto.DriverCategoryDTO;
import com.chocolate.chocolatefactory.model.dto.DriverDTO;
import com.chocolate.chocolatefactory.repository.DriverRepository;
import com.chocolate.chocolatefactory.service.DriverService;
import com.chocolate.chocolatefactory.service.EngagementService;

@Service
public class DriverServiceImpl implements DriverService{
    
    private final DriverRepository driverRepository;
    private final EngagementService engagementService;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, EngagementService engagementService){
        this.driverRepository=driverRepository;
        this.engagementService = engagementService;
    }

    @Override
    public Driver findById(Long id){
        Optional<Driver> driver = driverRepository.findById(id);

        if(!driver.isEmpty()){
            return new Driver(driver.get().getId(), driver.get().getFirstName(), driver.get().getLastName(), driver.get().getAdress(), driver.get().getPhoneNumber(), driver.get().getJmbg(), driver.get().getDeleted(), driver.get().getCategories(), driver.get().getUsername(), driver.get().getEmail());
        }
        return null;
    }

    @Override
    public List<Driver> findAll(){

        List<Driver> employedDrivers = new ArrayList<>();

        for(Driver driver : driverRepository.findAll()){
            if(!driver.getDeleted()){
                employedDrivers.add(driver);
            }
        }
        return employedDrivers;
    }

    @Override
    public DriverDTO create(DriverDTO driverDTO) throws Exception{
        
        List<DriverCategory> categories = new ArrayList<>();
        for(DriverCategoryDTO categoryDTO : driverDTO.getCategoriesDTO()){
            DriverCategory category = new DriverCategory(categoryDTO.getId(), categoryDTO.getCategoryMark(), categoryDTO.getMaxLoadCapacity());
            categories.add(category);
        }
        Driver driver = new Driver(driverDTO.getFirstName(), driverDTO.getLastName(), driverDTO.getAdress(), driverDTO.getPhoneNumber(), driverDTO.getJmbg(), false, categories, driverDTO.getUsername(), driverDTO.getEmail());

        Driver newDriver = driverRepository.save(driver);

        DriverDTO newDriverDTO = new DriverDTO(newDriver.getId(), newDriver.getFirstName(), newDriver.getLastName(), newDriver.getAdress(), newDriver.getPhoneNumber(), newDriver.getJmbg());

        return newDriverDTO;
    }

    @Override
    public DriverDTO update(DriverDTO driverDTO, Long id) throws Exception{

        Optional<Driver> driver = driverRepository.findById(id);

        if(!driverDTO.getFirstName().equals(""))
            driver.get().setFirstName(driverDTO.getFirstName());
        if(!driverDTO.getLastName().equals(""))
            driver.get().setLastName(driverDTO.getLastName());
        if(!driverDTO.getAdress().equals(""))
            driver.get().setAdress(driverDTO.getAdress());
        if(!driverDTO.getPhoneNumber().equals(""))
            driver.get().setPhoneNumber(driverDTO.getPhoneNumber());
        if(!driverDTO.getJmbg().equals(""))
            driver.get().setJmbg(driverDTO.getJmbg());
        //if(!driverDTO.getCategory().equals(""))
        //    driver.get().setCategory(driverDTO.getCategory());
        if(!driverDTO.getUsername().equals(""))
            driver.get().setUsername(driverDTO.getUsername());
        if(!driverDTO.getEmail().equals(""))
            driver.get().setEmail(driverDTO.getEmail());
        
        Driver driverToUpdate = new Driver(driver.get().getId(), driver.get().getFirstName(), driver.get().getLastName(), driver.get().getAdress(), driver.get().getPhoneNumber(), driver.get().getJmbg(), driver.get().getDeleted(), driver.get().getCategories(), driver.get().getUsername(), driver.get().getEmail());

        Driver updatedDriver = driverRepository.save(driverToUpdate);

        DriverDTO updatedDriverDTO  = new DriverDTO(updatedDriver.getId(), updatedDriver.getFirstName(), updatedDriver.getLastName(), updatedDriver.getAdress(), updatedDriver.getPhoneNumber(), updatedDriver.getJmbg());
        return updatedDriverDTO;
    }

    @Override
    public Boolean delete(Long id){
        Optional<Driver> driver = driverRepository.findById(id);

        for(Engagement engagement : engagementService.getEngagements()){
            if(engagement.getDriver().getId() == id){
                if(engagementService.setEngagementEndDate(engagement.getId())){

                    driver.get().setDeleted(true);
                    Driver driverToDelete = new Driver(driver.get().getId(), driver.get().getFirstName(), driver.get().getLastName(), driver.get().getAdress(), driver.get().getPhoneNumber(), driver.get().getJmbg(), driver.get().getDeleted(), driver.get().getCategories(), driver.get().getUsername(), driver.get().getEmail());
                    Driver deletedDriver = driverRepository.save(driverToDelete);

                    if(deletedDriver!=null){
                        return true;
                    }
                }
                return false;
            }
        }
        driver.get().setDeleted(true);
        Driver driverToDelete = new Driver(driver.get().getId(), driver.get().getFirstName(), driver.get().getLastName(), driver.get().getAdress(), driver.get().getPhoneNumber(), driver.get().getJmbg(), driver.get().getDeleted(), driver.get().getCategories(), driver.get().getUsername(), driver.get().getEmail());
        Driver deletedDriver = driverRepository.save(driverToDelete);

        if(deletedDriver!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Driver> getDriversAvailableForEngagement(){
        List<Driver> drivers = new ArrayList<>();

        for(Driver driver : findAll()){
            if(engagementService.isDriverFreeForEngagement(driver.getId())){
                drivers.add(driver);
            }
        }
        return drivers;
    }

}
