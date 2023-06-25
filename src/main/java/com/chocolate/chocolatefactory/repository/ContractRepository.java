package com.chocolate.chocolatefactory.repository;

import com.chocolate.chocolatefactory.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    List<Contract> findAll();

    Contract findOneById(Long id);

    List<Contract> findAllByCompanyIdAndIsTerminate(Long id, Boolean flag);

}
