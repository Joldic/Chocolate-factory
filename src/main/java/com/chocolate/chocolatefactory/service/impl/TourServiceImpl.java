package com.chocolate.chocolatefactory.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.Order;
import com.chocolate.chocolatefactory.model.OrderStatus;
import com.chocolate.chocolatefactory.model.Tour;
import com.chocolate.chocolatefactory.model.dto.EngagementDTO;
import com.chocolate.chocolatefactory.model.dto.OrderDTO;
import com.chocolate.chocolatefactory.model.dto.TourDTO;
import com.chocolate.chocolatefactory.repository.TourRepository;
import com.chocolate.chocolatefactory.service.EngagementService;
import com.chocolate.chocolatefactory.service.OrderService;
import com.chocolate.chocolatefactory.service.TourService;

@Service
public class TourServiceImpl implements TourService{
    
    private final TourRepository tourRepository;
    private final OrderService orderService;
    private final EngagementService engagementService;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, OrderService orderService, EngagementService engagementService){
        this.tourRepository = tourRepository;
        this.orderService = orderService;
        this.engagementService = engagementService;
    }

    @Override
    public Tour findById(Long id){
        Optional<Tour> tour = tourRepository.findById(id);

        if(!tour.isEmpty()){
            return new Tour(tour.get().getId(), tour.get().getCity(), tour.get().getDate(), tour.get().getEngagements(), tour.get().getOrders());
        }
        return null;
    }

    @Override
    public List<Tour> findAll(){
        return tourRepository.findAll();
    }

    @Override
    public List<Tour> createTransportationPlan(){

        List<Tour> tours = new ArrayList<Tour>();

        Hashtable<String, List<Order>> classifiedOrders = orderService.classifyOrdersByCity();

        Enumeration<String> citys = classifiedOrders.keys();
        
        while(citys.hasMoreElements()){

            LocalDate date = LocalDate.now().plusDays(1);

            String current = citys.nextElement().toString();

            List<Order> orders = classifiedOrders.get(current);

            Tour tour = new Tour(current, date, null, orders);
            tours.add(tour);
        }

        List<Pair<Float, Tour>> toursTotalWeight = calculateTourWeight(tours);
        List<Engagement> activeEngagements = new ArrayList<>();

        for(Engagement engagement : engagementService.getEngagements()){
            if(engagement.getTruck().getDriveability()){
                activeEngagements.add(engagement);
            }
        }

        toursTotalWeight.sort((p1, p2)->p1.getValue0().compareTo(p2.getValue0()));
        activeEngagements.sort((o1, o2)->o1.getTruck().getCapacity().compareTo(o2.getTruck().getCapacity()));

        List<Tour> toursWithEngagedTrucks = new ArrayList<Tour>();

        for(int i=activeEngagements.size()-1, j=toursTotalWeight.size()-1; i>=0 && j>=0; i--, j--){
            
            if(activeEngagements.get(i).getTruck().getCapacity() >= toursTotalWeight.get(j).getValue0()){
                Tour tour = toursTotalWeight.get(j).getValue1();
                List<Engagement> engagements = new ArrayList<>();
                engagements.add(activeEngagements.get(i));
                tour.setEngagements(engagements);
                toursWithEngagedTrucks.add(tour);
                activeEngagements.remove(i);
                toursTotalWeight.remove(j);
            }
            else{
                Tour tour = toursTotalWeight.get(j).getValue1();
                List<Engagement> engagements = new ArrayList<>();
                engagements.add(activeEngagements.get(i));
                for(int k=0; k<activeEngagements.size()-i; k++){
                    if((activeEngagements.get(i).getTruck().getCapacity()+activeEngagements.get(k).getTruck().getCapacity()) >= toursTotalWeight.get(j).getValue0()){  
                        engagements.add(activeEngagements.get(k));
                        tour.setEngagements(engagements);
                        toursWithEngagedTrucks.add(tour);
                        activeEngagements.remove(i);
                        activeEngagements.remove(k);
                        toursTotalWeight.remove(j);                      
                        break;
                    }
                }
            }
        }
        return toursWithEngagedTrucks;

    }

    @Override
    public List<Pair<Float, Tour>> calculateTourWeight(List<Tour> tours){
        
        List<Pair<Float, Tour>> toursTotalWeight = new ArrayList<Pair<Float, Tour>>();
        for(Tour tour : tours){
            Float totalWeight = 0.0f;
            for(Order order : tour.getOrders()){
                totalWeight+=order.getTotalWeight();
            }
            toursTotalWeight.add(new Pair<Float, Tour>(totalWeight, tour));
        }
        
        return toursTotalWeight;
    }

    @Override
    public List<Tour> acceptTransportationPlan(List<TourDTO> toursDTO){
        List<Tour> tours = new ArrayList<>();

        for(TourDTO tourDTO : toursDTO){

            List<Engagement> engagements = new ArrayList<>();
            for(EngagementDTO engagementDTO : tourDTO.getEngagementsDTO()){
                Engagement engagement = engagementService.findOne(engagementDTO.getId());
                engagements.add(engagement);
            }

            List<Order> orders = new ArrayList<>();
            for(OrderDTO orderDTO : tourDTO.getOrdersDTO()){
                Order order = orderService.findById(orderDTO.getId());
                orders.add(order);
            }

            Tour tour = new Tour(tourDTO.getCity(), tourDTO.getDate(), engagements, orders);
            Tour newTour = tourRepository.save(tour);
            for(Order order : newTour.getOrders()){
                orderService.changeOrderStatus(OrderStatus.Ready_For_Delivery, order.getId());
                orderService.setTour(order.getId(), newTour);
            }
            for(Engagement engagement : newTour.getEngagements()){
                engagementService.setTour(engagement.getId(), newTour);
            }
            tours.add(newTour);
        }
        return tours;
    }

    @Override
    public List<Tour> getTransportationPlan(LocalDate date){
        List<Tour> tours = new ArrayList<>();

        for(Tour tour : findAll()){
            if(tour.getDate().isEqual(date)){
                tours.add(tour);
            }
        }
        return tours;
    }

    @Override
    public List<Tour> getTransportationPlanForDriver(LocalDate date, String driverUsername){
        List<Tour> tours = new ArrayList<>();
        
        for(Tour tour : getTransportationPlan(date)){
            for(Engagement engagement : tour.getEngagements()){
                if(engagement.getDriver().getUsername().equals(driverUsername)){
                    tours.add(tour);
                }
            }
        }
        return tours;
    }
    @Override
    public List<Tour> getToursByMonth(String month){
        List<Tour> tours = new ArrayList<>();
        
        for(Tour tour : findAll()){
            if((tour.getDate().getMonth().toString()).equals(month)){
                tours.add(tour);
            }
        }

        return tours;
    }

    @Override 
    public List<Tour> getToursByDriver(Long driverId, String month){
        List<Tour> tours = new ArrayList<>();
        
        for(Tour tour : getToursByMonth(month)){ 
            for(Engagement engagement : tour.getEngagements()){
                if(engagement.getDriver().getId() == driverId){
                    tours.add(tour);
                }
            }
        }
        return tours;
    }

}
