package com.chocolate.chocolatefactory.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chocolate.chocolatefactory.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findById(Long id);

    List<Driver> findAll();

}

