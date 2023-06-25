package com.chocolate.chocolatefactory.service;

import java.util.List;

import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.Tour;
import com.chocolate.chocolatefactory.model.dto.EngagementDTO;

public interface EngagementService {
    
    List<Engagement> findAll();

    List<Engagement> getEngagements();

    List<Engagement> findByDriver(Long id);

    List<Engagement> findByTruck(Long id);

    Engagement create(EngagementDTO engagementDTO);

    Boolean isDriverFreeForEngagement(Long id);

    Boolean isTruckFreeForEngagement(Long id);

    Engagement findCurrentEngagementByDriver(Long id);

    Engagement findCurrentEngagementByTruck(Long id);

    Boolean setEngagementEndDate(Long id);

    Engagement findOne(Long id);

    Boolean setTour(Long id, Tour tour);

}
