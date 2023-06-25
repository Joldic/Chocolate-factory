package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByEmail(String email);

    Optional<Company> findById(Long id);

    List<Company> findAll();
}
