package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.WarehouseIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseIngredientRepository extends JpaRepository<WarehouseIngredient, Long> {
    List<WarehouseIngredient> findAll();

    List<WarehouseIngredient> findByIngredient(Ingredient ingredient);
}
