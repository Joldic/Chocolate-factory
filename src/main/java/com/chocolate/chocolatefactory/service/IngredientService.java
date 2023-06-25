package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    List<Ingredient> findAll();

    void delete(Ingredient ingredient) throws Exception;

    Ingredient update(Ingredient ingredient) throws Exception;

    Optional<Ingredient> findOne(Long id) throws Exception;

    Ingredient create(Ingredient ingredient) throws Exception;

    Ingredient findOneByName(String name) throws Exception;
}
