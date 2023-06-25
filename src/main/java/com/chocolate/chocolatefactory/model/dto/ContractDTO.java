package com.chocolate.chocolatefactory.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ContractDTO {

    private Long id;
    private String executionDate;
    private String terminationDate;
    private String purposeOfTheAgreement;
    private String scopeOfWork;
    private String terminationClause;
    private Boolean termsAndConditions;
    private Boolean confidentialityAndNonDisclosure;
    private String terminationReason;
    private List<ContractPartDTO> contractPartDTOs;


    public ContractDTO() {
    }

    public ContractDTO(Long id, String executionDate, String terminationDate, String purposeOfTheAgreement, String scopeOfWork, String terminationClause, Boolean termsAndConditions, Boolean confidentialityAndNonDisclosure, String terminationReason) {
        this.id = id;
        this.executionDate = executionDate;
        this.terminationDate = terminationDate;
        this.purposeOfTheAgreement = purposeOfTheAgreement;
        this.scopeOfWork = scopeOfWork;
        this.terminationClause = terminationClause;
        this.termsAndConditions = termsAndConditions;
        this.confidentialityAndNonDisclosure = confidentialityAndNonDisclosure;
        this.terminationReason = terminationReason;
    }

    public ContractDTO(String executionDate, String terminationDate, String purposeOfTheAgreement, String scopeOfWork, String terminationClause, Boolean termsAndConditions, Boolean confidentialityAndNonDisclosure, String terminationReason) {
        this.executionDate = executionDate;
        this.terminationDate = terminationDate;
        this.purposeOfTheAgreement = purposeOfTheAgreement;
        this.scopeOfWork = scopeOfWork;
        this.terminationClause = terminationClause;
        this.termsAndConditions = termsAndConditions;
        this.confidentialityAndNonDisclosure = confidentialityAndNonDisclosure;
        this.terminationReason = terminationReason;
    }
}
