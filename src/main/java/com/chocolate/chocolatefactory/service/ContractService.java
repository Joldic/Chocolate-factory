package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.Contract;
import com.chocolate.chocolatefactory.model.dto.ContractDTO;

import java.util.List;

public interface ContractService {

    ContractDTO create(ContractDTO contractDTO, Long companyId) throws Exception;

    List<ContractDTO> findAll();

    ContractDTO findOneById(Long id) throws Exception;

    List<ContractDTO> findAllByCompanyId(Long companyId) throws Exception;

    void delete(ContractDTO contractDTO) throws Exception;

    void renew(ContractDTO contractDTO) throws Exception;

}
