package com.chocolate.chocolatefactory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.Engagement;

@Repository
public interface EngagementRepository extends JpaRepository<Engagement, Long>{
    
    List<Engagement> findAll();

}
