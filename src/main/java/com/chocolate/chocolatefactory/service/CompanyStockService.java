package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.dto.CompanyStockDTO;

import java.util.List;

public interface CompanyStockService {

    List<CompanyStockDTO> findAllByCompanyId(Long companyId) throws Exception;
}
