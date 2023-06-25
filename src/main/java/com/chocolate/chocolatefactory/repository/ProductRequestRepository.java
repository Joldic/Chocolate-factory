package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {
    List<ProductRequest> findAll();
}
