package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.CompanyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyStockRepository extends JpaRepository<CompanyStock, Long> {

    List<CompanyStock> findAllByCompanyId(Long id);
}
