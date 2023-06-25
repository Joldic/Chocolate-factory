package com.chocolate.chocolatefactory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    Optional<Product> findById(Long id);
    List<Product> findAll();

    @Query("select p from Product p where p.price>=?1 and p.price<=?2")
    List<Product> filterByGrade(Double firstnumber, Double  secondnumber);

    List<Product> findByProductName(String name);

}
