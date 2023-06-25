package com.chocolate.chocolatefactory.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    
    Optional<Order> findById(Long id);
    List<Order> findAll();
}
