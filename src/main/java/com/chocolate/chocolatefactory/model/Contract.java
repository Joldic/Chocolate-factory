package com.chocolate.chocolatefactory.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "contracts")
public class Contract implements Serializable {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date executionDate;

    @Column
    private Date terminationDate;

    @Column
    private String purposeOfTheAgreement;

    @Column
    private String scopeOfWork;

    @Column
    private String terminationClause;

    @Column
    private Boolean termsAndConditions;

    @Column
    private Boolean confidentialityAndNonDisclosure;

    @Column
    private Boolean isTerminate;

    @Column
    private String terminationReason = null;

    @OneToMany(mappedBy = "contract")
    private List<ContractPart> contractParts;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    public Contract() {
    }

    public Contract(Long id, Date executionDate, Date terminationDate, String purposeOfTheAgreement, String scopeOfWork, String terminationClause, Boolean termsAndConditions, Boolean confidentialityAndNonDisclosure) {
        this.id = id;
        this.executionDate = executionDate;
        this.terminationDate = terminationDate;
        this.purposeOfTheAgreement = purposeOfTheAgreement;
        this.scopeOfWork = scopeOfWork;
        this.terminationClause = terminationClause;
        this.termsAndConditions = termsAndConditions;
        this.confidentialityAndNonDisclosure = confidentialityAndNonDisclosure;
        this.isTerminate = false;
    }
}
