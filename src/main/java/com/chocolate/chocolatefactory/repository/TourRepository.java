package com.chocolate.chocolatefactory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long>{
    
    Optional<Tour> findById(Long id);

    List<Tour> findAll();
}
