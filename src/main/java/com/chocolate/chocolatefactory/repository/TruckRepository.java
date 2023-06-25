package com.chocolate.chocolatefactory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.Truck;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    
    Optional<Truck> findById(Long id);
    List<Truck> findAll();
}
