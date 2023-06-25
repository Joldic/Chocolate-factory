package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.IngredientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRequestRepository extends JpaRepository<IngredientRequest, Long> {

    List<IngredientRequest> findAll();

}
