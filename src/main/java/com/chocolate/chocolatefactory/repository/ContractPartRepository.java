package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.ContractPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractPartRepository extends JpaRepository<ContractPart, Long> {

    List<ContractPart> findAllByContractId(Long contractId);

    List<Long> findAllIngredientIdByContractId(Long contractId);
}
