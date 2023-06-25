package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Contract;
import com.chocolate.chocolatefactory.model.ContractPart;
import com.chocolate.chocolatefactory.model.dto.ContractPartDTO;
import com.chocolate.chocolatefactory.repository.ContractPartRepository;
import com.chocolate.chocolatefactory.repository.IngredientRepository;
import com.chocolate.chocolatefactory.service.ContractPartService;
import com.chocolate.chocolatefactory.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractPartServiceImpl implements ContractPartService {

    private final ContractPartRepository contractPartRepository;
    private final IngredientService ingredientService;

    @Autowired
    public ContractPartServiceImpl(ContractPartRepository contractPartRepository, IngredientService ingredientService) {
        this.contractPartRepository = contractPartRepository;
        this.ingredientService = ingredientService;
    }

    @Override
    public List<ContractPart> findAllByContractId(Long contractId) throws Exception {
        return contractPartRepository.findAllByContractId(contractId);
    }

    @Override
    public List<Long> findAllIngredientIdByContractId(Long contractId) throws Exception {
        return contractPartRepository.findAllIngredientIdByContractId(contractId);
    }

    @Override
    public void create(List<ContractPartDTO> contractPartDTOs, Contract contract) throws Exception {

        for(ContractPartDTO contractPartDTO : contractPartDTOs){
            ContractPart contractPart = new ContractPart(
                    contractPartDTO.getPrice()
            );
            contractPart.setContract(contract);
            contractPart.setIngredient(ingredientService.findOneByName(contractPartDTO.getIngredientName()));
            contractPartRepository.save(contractPart);
        }
    }
}
