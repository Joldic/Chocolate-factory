package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.Contract;
import com.chocolate.chocolatefactory.model.ContractPart;
import com.chocolate.chocolatefactory.model.dto.ContractPartDTO;

import java.util.List;

public interface ContractPartService {

    List<ContractPart> findAllByContractId(Long contractId) throws Exception;

    List<Long> findAllIngredientIdByContractId(Long ingredientId) throws Exception;

    void create(List<ContractPartDTO> contractPartDTOs, Contract contract) throws Exception;
}
