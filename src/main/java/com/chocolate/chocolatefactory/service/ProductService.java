package com.chocolate.chocolatefactory.service;

import java.util.List;
import java.util.Optional;

import com.chocolate.chocolatefactory.model.Product;
import com.chocolate.chocolatefactory.model.dto.IngredientDTO;
import com.chocolate.chocolatefactory.model.dto.ProductDTO;

public interface ProductService {
    
    Optional<Product> findById(Long id);

    List<Product> findAll();

    ProductDTO create(ProductDTO productDTO) throws Exception;

    void delete(Long id);

    List<Product> filterByGrade(Double firstnumber, Double  secondnumber);

     List<Product> findByName(String name);

     List<IngredientDTO> getIngredientsForProduct(Long id);

     List<ProductDTO> getProductsWithIngredients();

     List<ProductDTO> getAllProductionRequests();
}
