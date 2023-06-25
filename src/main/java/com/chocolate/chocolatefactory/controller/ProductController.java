package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.dto.IngredientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chocolate.chocolatefactory.model.Product;
import com.chocolate.chocolatefactory.model.dto.ProductDTO;
import com.chocolate.chocolatefactory.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> findOneById(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);

        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductDTO productDTO = new ProductDTO(product.get().getId(), product.get().getProductName(), product.get().getPrice(), product.get().getWeight());
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> products = productService.findAll();
        List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();

        for(Product product : products){
            ProductDTO productDTO = new ProductDTO(product.getId(), product.getProductName(), product.getPrice(), product.getWeight());
            productsDTO.add(productDTO);
        }

        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO truckDTO) throws Exception{
        return new ResponseEntity<>(productService.create(truckDTO), HttpStatus.CREATED);
    }

 

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/filter/{firstnumber}/{secondnumber}")
    public ResponseEntity<List<ProductDTO>> filterCenters(@PathVariable("firstnumber") Double firstnumber,
            @PathVariable("secondnumber") Double secondnumber) {
        List<Product> products = this.productService.filterByGrade(firstnumber, secondnumber);

        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products) {
            ProductDTO productDTO = new ProductDTO(product.getId(), product.getProductName(), product.getPrice(), product.getWeight());
            productDTOS.add(productDTO);
        }

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductDTO>> 	getTrening(@PathVariable("name") String name) {
        // Pozivanjem metode servisa dobavljamo zaposlenog po ID-ju
        List<Product> products = this.productService.findByName(name);
   
        List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();
        for(Product t : products) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(t.getId());
            productDTO.setPrice(t.getPrice());
            productDTO.setProductName(t.getProductName());
          
                productsDTO.add(productDTO);
        }

        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
   }

   @GetMapping(value = "/recipe/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<IngredientDTO>> getRecipeForProduct(@PathVariable Long id){
       List<IngredientDTO> ingredients = this.productService.getIngredientsForProduct(id);

        return new ResponseEntity<>(ingredients, HttpStatus.OK);
   }

    @GetMapping(value = "/allProductsRecipes", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<ProductDTO>> getRecipeForProduct(){
        List<ProductDTO> products = this.productService.getProductsWithIngredients();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

   @GetMapping(value = "/productionRequests", produces = MediaType.APPLICATION_JSON_VALUE)
   @PreAuthorize("hasRole('ROLE_PRODUCTION_MANAGER')")
    public ResponseEntity<List<ProductDTO>> getAllProductionRequests(){
        List<ProductDTO> productDTOS = this.productService.getAllProductionRequests();

        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
   }
}
