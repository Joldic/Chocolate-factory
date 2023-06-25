package com.chocolate.chocolatefactory.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.Order;
import com.chocolate.chocolatefactory.model.OrderStatus;
import com.chocolate.chocolatefactory.model.Tour;
import com.chocolate.chocolatefactory.repository.OrderRepository;
import com.chocolate.chocolatefactory.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    @Override
    public List<Order>findByStatus(OrderStatus status){
        List<Order> orders = new ArrayList<Order>();

        for (Order order : findAll()){
            if(order.getStatus() == status){

                if(status == OrderStatus.Completed){
                    if(order.getDate().isEqual(LocalDate.now()) || order.getDate().isBefore(LocalDate.now())){
                        setPriority(order.getId());
                    }
                }
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public Hashtable<String, List<Order>> classifyOrdersByCity(){
        List<Order> completedOrders = findByStatus(OrderStatus.Completed);

        Hashtable<String, List<Order>> classifiedOrders = new Hashtable<>();

        for(Order order : completedOrders){
            if(!classifiedOrders.containsKey(order.getCity())){
                List<Order> orders = new ArrayList<Order>();
                orders.add(order);
                classifiedOrders.put(order.getCity(), orders);
            }else{
                List<Order> orders = classifiedOrders.get(order.getCity());
                orders.add(order);
                classifiedOrders.put(order.getCity(), orders);
            }
        }
        return classifiedOrders;
    }

    @Override
    public Order findById(Long id){
        Optional<Order> order = orderRepository.findById(id);

        if(!order.isEmpty()){
            return new Order(order.get().getId(), order.get().getAddress(), order.get().getCity(), order.get().getDate(), order.get().getTotalPrice(), order.get().getTotalWeight(), order.get().getStatus(), order.get().getPriority(), order.get().getUser(), order.get().getTour(), order.get().getOrderedProducts());
        }
        return null;
    }
    @Override
    public void delete(Long id){
       orderRepository.deleteById(id);
    }

    @Override
    public Boolean changeOrderStatus(OrderStatus status, Long id){

        Order orderToChange = findById(id);

        orderToChange.setStatus(status);

        Order order = new Order(orderToChange.getId(), orderToChange.getAddress(), orderToChange.getCity(), orderToChange.getDate(), orderToChange.getTotalPrice(), orderToChange.getTotalWeight(), orderToChange.getStatus(), orderToChange.getPriority(), orderToChange.getUser(), orderToChange.getTour(), orderToChange.getOrderedProducts());

        Order updatedOrder = orderRepository.save(order);

        if(updatedOrder == null){
            return false;
        }
        return true;
    }

    @Override
    public Order changeOrderStatusAndDate(OrderStatus status, Long id, LocalDate date){
        Order orderToChange = findById(id);

        orderToChange.setStatus(status);

        orderToChange.setDate(date);

        Order order = new Order(orderToChange.getId(), orderToChange.getAddress(), orderToChange.getCity(), orderToChange.getDate(), orderToChange.getTotalPrice(), orderToChange.getTotalWeight(), orderToChange.getStatus(), orderToChange.getPriority(), orderToChange.getUser(), orderToChange.getTour(), orderToChange.getOrderedProducts());

        Order updatedOrder = orderRepository.save(order);

        return updatedOrder;
    }

    @Override 
    public Boolean setTour(Long orderId, Tour tour){
        Order orderToChange = findById(orderId);

        orderToChange.setTour(tour);
        Order order = new Order(orderToChange.getId(), orderToChange.getAddress(), orderToChange.getCity(), orderToChange.getDate(), orderToChange.getTotalPrice(), orderToChange.getTotalWeight(), orderToChange.getStatus(), orderToChange.getPriority(), orderToChange.getUser(), orderToChange.getTour(), orderToChange.getOrderedProducts());
        Order updatedOrder = orderRepository.save(order);

        if(updatedOrder == null){
            return false;
        }
        return true;
    }

    @Override
    public Boolean setPriority(Long id){
        Order orderToChange = findById(id);
        orderToChange.setPriority(true);

        Order order = new Order(orderToChange.getId(), orderToChange.getAddress(), orderToChange.getCity(), orderToChange.getDate(), orderToChange.getTotalPrice(), orderToChange.getTotalWeight(), orderToChange.getStatus(), orderToChange.getPriority(), orderToChange.getUser(), orderToChange.getTour(), orderToChange.getOrderedProducts());

        Order updatedOrder = orderRepository.save(order);

        if(updatedOrder == null){
            return false;
        }
        return true;
    }

    @Override
    public List<Order> getDeliveredOrdersByMonth(String month){
        List<Order> orders = new ArrayList<>();

        for(Order order : findAll()){
            if(order.getStatus() == OrderStatus.Delivered){
                if((order.getTour().getDate().getMonth().toString()).equals(month)){
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    @Override
    public List<Order> getFinishedOrdersByMonth(String month){
        List<Order> orders = new ArrayList<>();
        for(Order order : findAll()){
            if(order.getStatus() == OrderStatus.Approved){
                if((order.getDate().getMonth().toString()).equals(month)){
                    orders.add(order);
                }
            }
        }
        return orders;
    }
}
