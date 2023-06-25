package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.dto.CompanyStockDTO;
import com.chocolate.chocolatefactory.service.CompanyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/companyStocks")
public class CompanyStockController {

    private final CompanyStockService companyStockService;

    @Autowired
    public CompanyStockController(CompanyStockService companyStockService) {
        this.companyStockService = companyStockService;
    }

    @GetMapping(value = "/findAllByCompanyId/{companyId}")
    public ResponseEntity<List<CompanyStockDTO>> findAllByCompanyId(@PathVariable Long companyId) throws Exception{
        return new ResponseEntity<>(companyStockService.findAllByCompanyId(companyId), HttpStatus.OK);
    }
}
