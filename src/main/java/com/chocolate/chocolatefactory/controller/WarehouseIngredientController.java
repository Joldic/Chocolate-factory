package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.WarehouseIngredient;
import com.chocolate.chocolatefactory.model.dto.WarehouseIngredientDTO;
import com.chocolate.chocolatefactory.service.IngredientService;
import com.chocolate.chocolatefactory.service.WarehouseIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "api/warehouse")
public class WarehouseIngredientController {
    private final WarehouseIngredientService warehouseIngredientService;
    private final IngredientService ingredientService;

    @Autowired
    public WarehouseIngredientController(WarehouseIngredientService warehouseIngredientService, IngredientService ingredientService){
        this.warehouseIngredientService = warehouseIngredientService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<WarehouseIngredientDTO>> getIngredientsFromWarehouse(){
        List<WarehouseIngredient> warehouseIngredients = this.warehouseIngredientService.findAll();
        List<WarehouseIngredientDTO> warehouseIngredientDTOS = createListDTO(warehouseIngredients);

        return new ResponseEntity<>(warehouseIngredientDTOS, HttpStatus.OK);
    }

    public List<WarehouseIngredientDTO> createListDTO(List<WarehouseIngredient> warehouseIngredients){
        List<WarehouseIngredientDTO> wiDTO = new ArrayList<WarehouseIngredientDTO>();
        for(WarehouseIngredient wi : warehouseIngredients){
            WarehouseIngredientDTO dto = new WarehouseIngredientDTO(wi.getId(), wi.getIngredient().getName(), wi.getIngredient().getId(), wi.getQuantity());
            wiDTO.add(dto);
        }
        return wiDTO;
    }
}
