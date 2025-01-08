package com.example.nemesys.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Sample {

    private String siteId;
    private String siteName;
    private Map<NematodeGenus, Integer> nematodes;

}
