package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.Product;
import com.chocolate.chocolatefactory.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByProduct(Product product);
}
