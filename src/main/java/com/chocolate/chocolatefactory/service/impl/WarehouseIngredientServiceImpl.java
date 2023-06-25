package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.WarehouseIngredient;
import com.chocolate.chocolatefactory.repository.IngredientRepository;
import com.chocolate.chocolatefactory.repository.WarehouseIngredientRepository;
import com.chocolate.chocolatefactory.service.WarehouseIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WarehouseIngredientServiceImpl implements WarehouseIngredientService {
    private final WarehouseIngredientRepository warehouseIngredientRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public WarehouseIngredientServiceImpl(WarehouseIngredientRepository warehouseIngredientRepository, IngredientRepository ingredientRepository ){
        this.warehouseIngredientRepository = warehouseIngredientRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<WarehouseIngredient> findAll(){
        return this.warehouseIngredientRepository.findAll();
    }

    @Override
    public Boolean update(Hashtable<Long, Integer> hashtable){
        Iterator<Map.Entry<Long, Integer>> itr = hashtable.entrySet().iterator();

        Map.Entry<Long, Integer> entry = null;
        while(itr.hasNext()){
            entry = itr.next();

            Optional<Ingredient> ingredient = this.ingredientRepository.findById(entry.getKey());
            List<WarehouseIngredient> warehouseIngredients = this.warehouseIngredientRepository.findByIngredient(ingredient.get());
            for(WarehouseIngredient wi : warehouseIngredients){
                wi.setQuantity(wi.getQuantity() - entry.getValue());
                this.warehouseIngredientRepository.save(wi);
            }
        }

        return true;
    }
}
