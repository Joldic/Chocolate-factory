package com.chocolate.chocolatefactory.service;

import com.chocolate.chocolatefactory.model.Company;
import com.chocolate.chocolatefactory.model.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {

    Company findByEmail(String email);

    Company findById(Long id);

    List<CompanyDTO> findAll();
}
