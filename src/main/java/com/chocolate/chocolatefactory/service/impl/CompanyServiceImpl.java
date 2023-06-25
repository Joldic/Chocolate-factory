package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Company;
import com.chocolate.chocolatefactory.model.dto.CompanyDTO;
import com.chocolate.chocolatefactory.repository.CompanyRepository;
import com.chocolate.chocolatefactory.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    @Override
    public Company findById(Long id) {
        return companyRepository.findById(id).get();
    }

    @Override
    public List<CompanyDTO> findAll() {
        List<CompanyDTO> companyDTOS = new ArrayList<>();
            for (Company company: companyRepository.findAll()) {
                companyDTOS.add(new CompanyDTO(
                        company.getId(),
                        company.getName(),
                        company.getCountry(),
                        company.getCity(),
                        company.getAddress(),
                        company.getPhoneNumber(),
                        company.getEmail()
                        )
                );
            }
        return companyDTOS;
    }
}
