package com.chocolate.chocolatefactory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.DriverCategory;

@Repository
public interface DriverCategoryRepository extends JpaRepository<DriverCategory, Long> { 
    
    List<DriverCategory> findAll();
}
