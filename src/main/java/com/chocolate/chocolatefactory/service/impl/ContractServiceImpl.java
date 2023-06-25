package com.chocolate.chocolatefactory.service.impl;

import com.chocolate.chocolatefactory.model.Company;
import com.chocolate.chocolatefactory.model.Contract;
import com.chocolate.chocolatefactory.model.ContractPart;
import com.chocolate.chocolatefactory.model.Ingredient;
import com.chocolate.chocolatefactory.model.dto.ContractDTO;
import com.chocolate.chocolatefactory.model.dto.ContractPartDTO;
import com.chocolate.chocolatefactory.repository.ContractRepository;
import com.chocolate.chocolatefactory.service.CompanyService;
import com.chocolate.chocolatefactory.service.ContractPartService;
import com.chocolate.chocolatefactory.service.ContractService;
import com.chocolate.chocolatefactory.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final CompanyService companyService;
    private final ContractPartService contractPartService;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository, CompanyService companyService, ContractPartService contractPartService) {
        this.contractRepository = contractRepository;
        this.companyService = companyService;
        this.contractPartService = contractPartService;
    }

    @Override
    public ContractDTO create(ContractDTO contractDTO, Long companyId) throws Exception {

        //TODO obrisati kurbla

        ContractPartDTO contractPartDTO = new ContractPartDTO(40.0, "Milk");
        ContractPartDTO contractPartDTO2 = new ContractPartDTO(35.0, "Sugar");
        List<ContractPartDTO> contractPartDTOList = new ArrayList<>();
        contractPartDTOList.add(contractPartDTO);
        contractPartDTOList.add(contractPartDTO2);

        contractDTO.setContractPartDTOs(contractPartDTOList);

        //TODO do ovde je kurbla

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Contract newContract = new Contract(
                contractDTO.getId(),
                dateFormat.parse("05-18-2023"),
                dateFormat.parse(contractDTO.getTerminationDate()),
                contractDTO.getPurposeOfTheAgreement(),
                contractDTO.getScopeOfWork(),
                contractDTO.getTerminationClause(),
                contractDTO.getTermsAndConditions(),
                contractDTO.getConfidentialityAndNonDisclosure()
        );
        newContract.setCompany(companyService.findById(companyId));

        contractRepository.save(newContract);

        contractPartService.create(contractDTO.getContractPartDTOs(), newContract);

        return contractDTO;
    }

    @Override
    public void delete(ContractDTO contractDTO) throws Exception {
        Contract contractToDelete = contractRepository.findOneById(contractDTO.getId());
        contractToDelete.setIsTerminate(true);
        contractToDelete.setTerminationReason(contractDTO.getTerminationReason());
        contractRepository.save(contractToDelete);
    }

    @Override
    public void renew(ContractDTO contractDTO) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Contract contractToRenew = contractRepository.findOneById(contractDTO.getId());
        contractToRenew.setTerminationDate(dateFormat.parse(contractDTO.getTerminationDate()));
        contractRepository.save(contractToRenew);
    }

    @Override
    public List<ContractDTO> findAll() {

        List<Contract> contracts = contractRepository.findAll();
        List<ContractDTO> contractDTOS = new ArrayList<>();

        for(Contract contract : contracts){
            ContractDTO contractDTO = new ContractDTO(); //TODO
            contractDTOS.add(contractDTO);
        }

        return contractDTOS;
    }


    @Override
    public ContractDTO findOneById(Long id) throws Exception{
        Contract contract = contractRepository.findOneById(id);
        ContractDTO contractDTO = new ContractDTO(); //TODO
        return contractDTO;
    }

    @Override
    public List<ContractDTO> findAllByCompanyId(Long companyId) throws Exception {

        List<Contract> contracts = contractRepository.findAllByCompanyIdAndIsTerminate(companyId, false);

        List<ContractPart> contractParts = new ArrayList<>();

        for (Contract contract: contracts) {
            contractParts.addAll(contractPartService.findAllByContractId(contract.getId()));
        }

        List<ContractDTO> contractDTOS = new ArrayList<>();


            for (Contract contract: contracts) {

                ContractDTO contractDTO = new ContractDTO(
                        contract.getId(),
                        contract.getExecutionDate().toString(),
                        contract.getTerminationDate().toString(),
                        contract.getPurposeOfTheAgreement(),
                        contract.getScopeOfWork(),
                        contract.getTerminationClause(),
                        contract.getTermsAndConditions(),
                        contract.getConfidentialityAndNonDisclosure(),
                        contract.getTerminationReason()
                );

                List<ContractPartDTO> contractPartDTOs = new ArrayList<>();

                for(ContractPart contractPart : contractParts){
                    ContractPartDTO contractPartDTO = new ContractPartDTO(
                            contractPart.getId(),
                            contractPart.getPrice(),
                            contractPart.getIngredient().getName()
                    );
                    contractPartDTOs.add(contractPartDTO);
                };
                contractDTO.setContractPartDTOs(contractPartDTOs);
                contractDTOS.add(contractDTO);
            }

        return contractDTOS;
    }


}
