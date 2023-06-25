package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.dto.ContractDTO;
import com.chocolate.chocolatefactory.model.dto.IngredientRequestDTO;
import com.chocolate.chocolatefactory.service.IngredientRequestService;
import com.chocolate.chocolatefactory.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/ingredientRequests")
public class IngredientRequestController {

    private final IngredientRequestService ingredientRequestService;

    @Autowired
    public IngredientRequestController(IngredientRequestService ingredientRequestService) {
        this.ingredientRequestService = ingredientRequestService;
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<IngredientRequestDTO>> findAll() throws Exception{
        return new ResponseEntity<>(ingredientRequestService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/getLatest", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<IngredientRequestDTO>> getLatest() throws Exception{
        List<IngredientRequestDTO> ret_val = this.ingredientRequestService.getLatest();

        return new ResponseEntity<>(ret_val, HttpStatus.OK);
    }
}
