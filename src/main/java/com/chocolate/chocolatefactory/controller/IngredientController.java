package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.dto.IngredientDTO;
import com.chocolate.chocolatefactory.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping(value="api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {this.ingredientService = ingredientService;}

    @GetMapping(value = "/getIngredient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<IngredientDTO> getIngredient(@PathVariable Long id) throws Exception{
        Optional<Ingredient> ingredient = this.ingredientService.findOne(id);

        IngredientDTO ingredientDTO = createDTOInstance(ingredient.get());

        return new ResponseEntity<>(ingredientDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/getAllIngredients", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<IngredientDTO>> getAllIngredients(){
        List<Ingredient> ingredients = this.ingredientService.findAll();

        List<IngredientDTO> ingredientDTOS = new ArrayList<IngredientDTO>();

        for(Ingredient i : ingredients){
            IngredientDTO ingredientDTO = createDTOInstance(i);
            ingredientDTOS.add(ingredientDTO);
        }

        return new ResponseEntity<>(ingredientDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/createNewIngredient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredientDTO) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Ingredient ingredient = new Ingredient(
                ingredientDTO.getName()
        );

        Ingredient newIngredient = this.ingredientService.create(ingredient);

        IngredientDTO newIngredientDTO = createDTOInstance(newIngredient);

        return new ResponseEntity<>(newIngredientDTO, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/deleteIngredient/{id}")
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity deleteIngredient(@PathVariable Long id) throws Exception{
        Optional<Ingredient> ingredient = this.ingredientService.findOne(id);
        this.ingredientService.delete(ingredient.get());

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping(value = "/updateIngredient", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<IngredientDTO> updateIngredient(@RequestBody IngredientDTO ingredientDTO) throws Exception{
        Optional<Ingredient> oldIngredient = this.ingredientService.findOne(ingredientDTO.getId());

        Ingredient newIngredient = createRegularInstance(ingredientDTO);

        Ingredient updatedIngredient = this.ingredientService.update(newIngredient);
        IngredientDTO updatedIngredientDTO = createDTOInstance(updatedIngredient);

        return new ResponseEntity<>(updatedIngredientDTO, HttpStatus.OK);
    }

    public IngredientDTO createDTOInstance(Ingredient ingredient){
        IngredientDTO ret_val = new IngredientDTO(
                ingredient.getId(),
                ingredient.getName()
        );
        return ret_val;
    }

    public Ingredient createRegularInstance(IngredientDTO ingredientDTO) throws Exception{

        Ingredient ingredient = new Ingredient(
                ingredientDTO.getId(),
                ingredientDTO.getName()
        );
        return ingredient;
    }
}
