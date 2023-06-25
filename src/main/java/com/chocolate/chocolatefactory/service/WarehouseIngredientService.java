package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.WarehouseIngredient;
import com.chocolate.chocolatefactory.repository.WarehouseIngredientRepository;

import java.util.Hashtable;
import java.util.List;

public interface WarehouseIngredientService {
    List<WarehouseIngredient> findAll();
    Boolean update(Hashtable<Long, Integer> hashtable);
}
