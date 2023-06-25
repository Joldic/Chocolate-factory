package com.chocolate.chocolatefactory.service;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

import com.chocolate.chocolatefactory.model.Order;
import com.chocolate.chocolatefactory.model.OrderStatus;
import com.chocolate.chocolatefactory.model.Tour;

public interface OrderService {
    
    List<Order> findAll();

    List<Order> findByStatus(OrderStatus status);

    Hashtable<String, List<Order>> classifyOrdersByCity();

    Order findById(Long id);

    void delete(Long id);

    Boolean changeOrderStatus(OrderStatus status, Long id);

    Order changeOrderStatusAndDate(OrderStatus status, Long id, LocalDate date);

    Boolean setTour(Long orderId, Tour tour);

    Boolean setPriority(Long id);

    List<Order> getDeliveredOrdersByMonth(String month);
    List<Order> getFinishedOrdersByMonth(String month);

}
