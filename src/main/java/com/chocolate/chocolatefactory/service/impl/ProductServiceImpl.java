package com.chocolate.chocolatefactory.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.ProductRequest;
import com.chocolate.chocolatefactory.model.Recipe;
import com.chocolate.chocolatefactory.model.dto.IngredientDTO;
import com.chocolate.chocolatefactory.repository.IngredientRepository;
import com.chocolate.chocolatefactory.repository.ProductRequestRepository;
import com.chocolate.chocolatefactory.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chocolate.chocolatefactory.model.Product;
import com.chocolate.chocolatefactory.model.dto.ProductDTO;
import com.chocolate.chocolatefactory.repository.ProductRepository;
import com.chocolate.chocolatefactory.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    
    private final ProductRepository productRepository;
    private final RecipeRepository recipeRepository;

    private final IngredientRepository ingredientRepository;

    private final ProductRequestRepository productRequestRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository, ProductRequestRepository productRequestRepository){
        this.productRepository=productRepository;
        this.recipeRepository=recipeRepository;
        this.ingredientRepository=ingredientRepository;
        this.productRequestRepository = productRequestRepository;
    }

    @Override
    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    @Override 
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public ProductDTO create(ProductDTO productDTO) throws Exception{

        Product product = new Product(productDTO.getProductName(), productDTO.getPrice(), productDTO.getWeight());
        Product newProduct = productRepository.save(product);

        return new ProductDTO(newProduct.getId(), newProduct.getProductName(), newProduct.getPrice(), newProduct.getWeight());
    }

    @Override
    public void delete(Long id){
       productRepository.deleteById(id);
    }

    @Override
    public List<Product> filterByGrade(Double firstnumber, Double  secondnumber){
        return this.productRepository.filterByGrade(firstnumber, secondnumber);
    }

    
    @Override
    public List<Product> findByName(String name){
        List<Product> product = this.productRepository.findByProductName( name);
        if(product == null) {
            return null;
        }
        return product;
    }

    @Override
    public List<IngredientDTO> getIngredientsForProduct(Long id){
        Optional<Product> product = this.productRepository.findById(id);
        List<Recipe> recipes = this.recipeRepository.findAllByProduct(product.get());
        List<IngredientDTO> ingredientDTOS = new ArrayList<IngredientDTO>();

        for(Recipe r : recipes){
            Optional<Ingredient> i = this.ingredientRepository.findById(r.getIngredient().getId());
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setQuantity(r.getQuantity());
            ingredientDTO.setName(i.get().getName());
            ingredientDTO.setId(i.get().getId());
            ingredientDTOS.add(ingredientDTO);
        }

        return ingredientDTOS;
    }

    @Override
    public List<ProductDTO> getProductsWithIngredients(){
        List<ProductDTO> ret_val = new ArrayList<ProductDTO>();
        List<Product> allProducts = this.productRepository.findAll();
        for(Product p : allProducts){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(p.getId());
            productDTO.setProductName(p.getProductName());

            List<Recipe> recipes = this.recipeRepository.findAllByProduct(p);
            List<IngredientDTO> ingredientDTOS = new ArrayList<IngredientDTO>();
            for(Recipe r : recipes){
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setName(r.getIngredient().getName());
                ingredientDTO.setQuantity(r.getQuantity());

                ingredientDTOS.add(ingredientDTO);
            }
            productDTO.setIngredientDTOList(ingredientDTOS);
            ret_val.add(productDTO);
        }
        return ret_val;
    }

    @Override
    public List<ProductDTO> getAllProductionRequests(){
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        List<ProductRequest> requests = this.productRequestRepository.findAll();

        for(ProductRequest p : requests){
            ProductDTO productDTO = new ProductDTO();
            Product product = p.getProduct();
            productDTO.setProductName(product.getProductName());
            //productDTO.setQuantity(p.getQuantity());
            productDTO.setId(product.getId());
            productDTOS.add(productDTO);
        }

        return productDTOS;
    }

}
