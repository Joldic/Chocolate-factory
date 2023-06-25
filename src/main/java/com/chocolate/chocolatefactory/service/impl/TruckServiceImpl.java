package com.chocolate.chocolatefactory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.Driver;
import com.chocolate.chocolatefactory.model.DriverCategory;
import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.Truck;
import com.chocolate.chocolatefactory.model.dto.TruckDTO;
import com.chocolate.chocolatefactory.repository.TruckRepository;
import com.chocolate.chocolatefactory.service.DriverService;
import com.chocolate.chocolatefactory.service.EngagementService;
import com.chocolate.chocolatefactory.service.TruckService;

@Service
public class TruckServiceImpl implements TruckService{
    
    private final TruckRepository truckRepository;
    private final EngagementService engagementService;
    private final DriverService driverService;

    @Autowired
    public TruckServiceImpl(TruckRepository truckRepository, EngagementService engagementService, DriverService driverService){
        this.truckRepository=truckRepository;
        this.engagementService = engagementService;
        this.driverService = driverService;
    }

    @Override
    public Truck findById(Long id){
        Optional<Truck> truck = truckRepository.findById(id);

        if(!truck.isEmpty()){
            return new Truck(truck.get().getId(), truck.get().getRegistrationNumber(), truck.get().getName(), truck.get().getCapacity(), truck.get().getDriveability(), truck.get().getDeleted());
        }
        return null;
    }

    @Override 
    public List<Truck> findAll(){
        List<Truck> availableTrucks = new ArrayList<>();

        for(Truck truck : truckRepository.findAll()){
            if(!truck.getDeleted()){
                availableTrucks.add(truck);
            }
        }
        return availableTrucks;
    }

    @Override
    public TruckDTO create(TruckDTO truckDTO) throws Exception{

        Truck truck = new Truck(truckDTO.getRegistrationNumber(), truckDTO.getName(), truckDTO.getCapacity(), truckDTO.getDriveability(), false);
        Truck newTruck = truckRepository.save(truck);

        return new TruckDTO(newTruck.getId(), newTruck.getRegistrationNumber(), newTruck.getName(), newTruck.getCapacity(), newTruck.getDriveability());
        
    }

    @Override
    public TruckDTO update(TruckDTO truckDTO, Long id) throws Exception{

        Optional<Truck> truck = truckRepository.findById(id);

        if(!truckDTO.getRegistrationNumber().equals(""))
            truck.get().setRegistrationNumber(truckDTO.getRegistrationNumber());
        if(!truckDTO.getName().equals(""))
            truck.get().setName(truckDTO.getName());
        truck.get().setDriveability(truckDTO.getDriveability());
        truck.get().setCapacity(truckDTO.getCapacity());
        
        Truck truckToUpdate = new Truck(truck.get().getId(), truck.get().getRegistrationNumber(), truck.get().getName(), truck.get().getCapacity(), truck.get().getDriveability(), truck.get().getDeleted());
        Truck updatedTruck = truckRepository.save(truckToUpdate);
        
        return new TruckDTO(updatedTruck.getId(), updatedTruck.getRegistrationNumber(), updatedTruck.getName(), updatedTruck.getCapacity(), updatedTruck.getDriveability());
    }

    @Override
    public Boolean delete(Long id){
        Optional<Truck> truck = truckRepository.findById(id);

        truck.get().setDeleted(true);

        Truck truckToDelete = new Truck(truck.get().getId(), truck.get().getRegistrationNumber(), truck.get().getName(), truck.get().getCapacity(), truck.get().getDriveability(), truck.get().getDeleted());

        for(Engagement engagement : engagementService.getEngagements()){
            if(engagement.getTruck().getId() == id){
                if(engagementService.setEngagementEndDate(engagement.getId())){
                    Truck deletedTruck = truckRepository.save(truckToDelete);

                    if(deletedTruck!=null){
                        return true;
                    }
                }
                return false;
            }
        }
        Truck deletedTruck = truckRepository.save(truckToDelete);

        if(deletedTruck!=null){
            return true;
        }
        return false;
    }

    @Override
    public List<Truck> getAvailableTrucksByDriverCategory(Long id){

        Driver driver = driverService.findById(id);
    
        List<Truck> trucks = new ArrayList<>();

        List<DriverCategory> driverCategories = driver.getCategories();

        driverCategories.sort((o1, o2)->o1.getMaxLoadCapacity().compareTo(o2.getMaxLoadCapacity()));
        for(Truck truck : findAll()){
            if(engagementService.isTruckFreeForEngagement(truck.getId())){
                if(driverCategories.get(driverCategories.size()-1).getMaxLoadCapacity()>=truck.getCapacity()){
                    trucks.add(truck);
                }
            }
        }
        
        return trucks;
    }
}
