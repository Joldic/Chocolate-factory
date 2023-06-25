package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.CompanyStock;
import com.chocolate.chocolatefactory.model.dto.CompanyStockDTO;
import com.chocolate.chocolatefactory.repository.CompanyStockRepository;
import com.chocolate.chocolatefactory.service.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyStockServiceImpl implements CompanyStockService {

    private final CompanyStockRepository companyStockRepository;

    @Autowired
    public CompanyStockServiceImpl(CompanyStockRepository companyStockRepository) {
        this.companyStockRepository = companyStockRepository;
    }

    @Override
    public List<CompanyStockDTO> findAllByCompanyId(Long companyId) throws Exception {

        List<CompanyStockDTO> companyStockDTOs = new ArrayList<>();

        for(CompanyStock companyStock : companyStockRepository.findAllByCompanyId(companyId)){
            CompanyStockDTO companyStockDTO = new CompanyStockDTO(
                    companyStock.getId(),
                    companyStock.getPrice(),
                    companyStock.getQuantity(),
                    companyStock.getExpiringDate().toString()
            );
            companyStockDTO.setIngredientName(companyStock.getIngredient().getName());
            companyStockDTOs.add(companyStockDTO);
        }

        return companyStockDTOs;
    }
}
