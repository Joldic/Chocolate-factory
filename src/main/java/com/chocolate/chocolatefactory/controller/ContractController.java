package com.chocolate.chocolatefactory.controller;

import com.chocolate.chocolatefactory.model.dto.ContractDTO;
import com.chocolate.chocolatefactory.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/contracts")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping(value = "/create/{companyId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContractDTO> create(@RequestBody ContractDTO contractDTO, @PathVariable Long companyId) throws Exception{
        return new ResponseEntity<>(contractService.create(contractDTO, companyId), HttpStatus.CREATED);
    }

    @PutMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestBody ContractDTO contractDTO) throws Exception{
        contractService.delete(contractDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<ContractDTO>> findAll(){
        return new ResponseEntity<>(contractService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/findAllByCompanyId/{companyId}")
    public ResponseEntity<List<ContractDTO>> findAllByCompanyId(@PathVariable Long companyId) throws Exception{
        return new ResponseEntity<>(contractService.findAllByCompanyId(companyId), HttpStatus.OK);
    }

    @GetMapping(value = "/findOneById/{id}")
    public ResponseEntity<ContractDTO> findOneById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(contractService.findOneById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/contractRenewal")
    public ResponseEntity<Void> renew(@RequestBody ContractDTO contractDTO) throws Exception{
        contractService.renew(contractDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
