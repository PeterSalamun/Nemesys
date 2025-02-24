package com.example.nemesys.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class NematodeGenus {

    @Id
    private Long id;
    private String genusName;
    private Integer feeding;
    private Double basal;
    private Double enrichment;
    private Double structure;
    private Integer cp;
    private Double GenavgMass;
    private Double GenavgCPr;
    private Double GenavgCRs;
    private Double GenavgMFP;
    private Double GenavgEFP;
    private Double GenavgSFP;
    private Double GenavgHFP;
    private Double GenavgFFP;
    private Double GenavgBFP;
    private Double GenavgPFP;
    private Double StderrMass;
    private Double StderrCPr;
    private Double StderrCRs;
    private Double StderrMFP;
    private Double StderrEFP;
    private Double StderrSFP;
    private Double StderrHFP;
    private Double StderrFFP;
    private Double StderrBFP;
    private Double StderrPFP;

}
