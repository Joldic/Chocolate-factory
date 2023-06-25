package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.IngredientRequest;
import com.chocolate.chocolatefactory.model.dto.IngredientRequestDTO;

import java.util.List;

public interface IngredientRequestService {

    List<IngredientRequestDTO> findAll() throws Exception;
    Integer findMaxRequestFlag();

    IngredientRequest create(IngredientRequest ingredientRequest);

    List<IngredientRequestDTO>  getLatest();
}
