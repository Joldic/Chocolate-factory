package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.dto.CompanyDTO;
import com.chocolate.chocolatefactory.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<CompanyDTO>> findAll(){
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }
}
