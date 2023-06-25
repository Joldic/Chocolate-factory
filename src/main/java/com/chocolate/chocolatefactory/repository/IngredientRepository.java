package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findById(Long id);

    List<Ingredient> findAll();

    Ingredient findOneByName(String name);
}
