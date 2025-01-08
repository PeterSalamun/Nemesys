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
    private int abundance;

}
