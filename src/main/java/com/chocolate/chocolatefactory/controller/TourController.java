package com.chocolate.chocolatefactory.controller;

import java.time.LocalDate;
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

import com.chocolate.chocolatefactory.model.Engagement;
import com.chocolate.chocolatefactory.model.Order;
import com.chocolate.chocolatefactory.model.OrderedProduct;
import com.chocolate.chocolatefactory.model.Tour;
import com.chocolate.chocolatefactory.model.dto.DriverDTO;
import com.chocolate.chocolatefactory.model.dto.EngagementDTO;
import com.chocolate.chocolatefactory.model.dto.OrderDTO;
import com.chocolate.chocolatefactory.model.dto.OrderedProductDTO;
import com.chocolate.chocolatefactory.model.dto.ProductDTO;
import com.chocolate.chocolatefactory.model.dto.TourDTO;
import com.chocolate.chocolatefactory.model.dto.TruckDTO;
import com.chocolate.chocolatefactory.model.dto.UserDTO;
import com.chocolate.chocolatefactory.service.TourService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/tours")
public class TourController {
    
    private final TourService tourService;

    @Autowired
    public TourController(TourService tourService){
        this.tourService = tourService;
    }

    @GetMapping(value = "/createPlan", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TourDTO>> createTransportationPlan(){
        
        List<Tour> tours = tourService.createTransportationPlan();
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/acceptPlan", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TourDTO>> acceptPlan(@RequestBody List<TourDTO> toursDTO){
        List<Tour> tours = tourService.acceptTransportationPlan(toursDTO);

        List<TourDTO> newToursDTO = new ArrayList<>();
        for(Tour tour : tours){
            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            newToursDTO.add(tourDTO);
        }
        return new ResponseEntity<>(newToursDTO, HttpStatus.CREATED);
    }
    @GetMapping(value = "/getPlanForToday", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TourDTO>> getTransportationPlanForToday(){
        
        LocalDate date = LocalDate.now();

        List<Tour> tours = tourService.getTransportationPlan(date);
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getPlanForTomorrow", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TourDTO>> getTransportationPlanForTomorrow(){
        
        LocalDate date = LocalDate.now().plusDays(1);

        List<Tour> tours = tourService.getTransportationPlan(date);
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getPlanForTodayForDriver/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<List<TourDTO>> getTransportationPlanForTodayForDriver(@PathVariable String username){
        LocalDate date = LocalDate.now();

        List<Tour> tours = tourService.getTransportationPlanForDriver(date, username);
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getPlanForTomorrowForDriver/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DRIVER')")
    public ResponseEntity<List<TourDTO>> getTransportationPlanForTomorrowForDriver(@PathVariable String username){
        LocalDate date = LocalDate.now().plusDays(1);

        List<Tour> tours = tourService.getTransportationPlanForDriver(date, username);
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getToursByDriver/{id}/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TourDTO>> getToursByDriver(@PathVariable Long id, @PathVariable String month){
        
        List<Tour> tours = tourService.getToursByDriver(id, month);
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getToursByMonth/{month}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_DELIVERY_MANAGER')")
    public ResponseEntity<List<TourDTO>> getToursByMonth(@PathVariable String month){
        
        List<Tour> tours = tourService.getToursByMonth(month);
        List<TourDTO> toursDTO = new ArrayList<TourDTO>();

        for(Tour tour : tours){

            List<EngagementDTO> engagementsDTO = new ArrayList<>();
            for(Engagement engagement : tour.getEngagements()){
                DriverDTO driverDTO = new DriverDTO(engagement.getDriver().getId(), engagement.getDriver().getFirstName(), engagement.getDriver().getLastName(), engagement.getDriver().getAdress(), engagement.getDriver().getPhoneNumber(), engagement.getDriver().getJmbg());
                TruckDTO truckDTO = new TruckDTO(engagement.getTruck().getId(), engagement.getTruck().getRegistrationNumber(), engagement.getTruck().getName(), engagement.getTruck().getCapacity(), engagement.getTruck().getDriveability());

                EngagementDTO engagementDTO = new EngagementDTO(engagement.getId(), truckDTO, driverDTO);
                engagementsDTO.add(engagementDTO);
            }

            List<OrderDTO> ordersDTO = new ArrayList<OrderDTO>();

            for(Order order : tour.getOrders()){
                UserDTO userDTO = new UserDTO(order.getUser().getFirstName(), order.getUser().getLastName(), order.getUser().getEmail(), order.getUser().getUsername());

                List<OrderedProductDTO> orderedProductsDTO = new ArrayList<>();
                for(OrderedProduct orderedProduct : order.getOrderedProducts()){
                    ProductDTO productDTO = new ProductDTO(orderedProduct.getProduct().getId(), orderedProduct.getProduct().getProductName(), orderedProduct.getProduct().getPrice(), orderedProduct.getProduct().getWeight());
                    OrderedProductDTO orderedProductDTO = new OrderedProductDTO(null, productDTO, orderedProduct.getQuantity());
                    orderedProductsDTO.add(orderedProductDTO);
                }

                OrderDTO orderDTO = new OrderDTO(order.getId(), order.getAddress(), order.getCity(), order.getDate(), order.getTotalPrice(), order.getTotalWeight(), order.getStatus(), order.getPriority(), userDTO, orderedProductsDTO);
                ordersDTO.add(orderDTO);
            }

            TourDTO tourDTO = new TourDTO(tour.getId(), tour.getCity(), tour.getDate(), ordersDTO, engagementsDTO);
            toursDTO.add(tourDTO);
        }

        return new ResponseEntity<>(toursDTO, HttpStatus.OK);
    }
}
