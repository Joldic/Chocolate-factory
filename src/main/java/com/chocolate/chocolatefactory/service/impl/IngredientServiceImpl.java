package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.IngredientRequest;
import com.chocolate.chocolatefactory.repository.IngredientRepository;
import com.chocolate.chocolatefactory.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository){this.ingredientRepository = ingredientRepository;}

    @Override
    public List<Ingredient> findAll(){
        return this.ingredientRepository.findAll();
    }

    @Override
    public void delete(Ingredient ingredient) throws Exception{
        if(this.ingredientRepository.findById(ingredient.getId()) == null){
            throw new Exception("Ingredient you are trying to delete does not exist!");
        }
        this.ingredientRepository.delete(ingredient);
    }

    @Override
    public Ingredient update(Ingredient ingredient) throws Exception{
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findOne(Long id) throws Exception{
        Optional<Ingredient> ingredient = this.ingredientRepository.findById(id);
        if(ingredient == null){
            throw new Exception("Ingredient with this id does not exist!");
        }
        return ingredient;
    }

    @Override
    public Ingredient create(Ingredient ingredient) throws Exception{
        if(ingredient.getId() != null){
            throw new Exception("ID must be null!");
        }
        return this.ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient findOneByName(String name) throws Exception {
        return ingredientRepository.findOneByName(name);
    }


}
