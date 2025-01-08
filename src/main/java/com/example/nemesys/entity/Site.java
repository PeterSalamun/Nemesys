package com.example.nemesys.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Site {

    private Long siteId;
    private String siteName;
    private List<Sample> sampleList;
}
