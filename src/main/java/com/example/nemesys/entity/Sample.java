package com.example.nemesys.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sample {

    @Id
    private String siteId;
    private Double abundance;
    private String siteName;

}
