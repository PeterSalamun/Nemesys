package com.example.nemesys.controllers;

import com.example.nemesys.entity.NematodeGenus;
import com.example.nemesys.services.IndicesCalculation;
import com.example.nemesys.services.ScrapingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BasicController {

    @Autowired
    private IndicesCalculation indicesCalculation;
    @Autowired
    private ScrapingService scrapingService;

    @GetMapping("/nemesys")
    public String indexPage() {
        return "Hello, World!";
    }

    @GetMapping("/nemesys/calculate")
    public String indicesCalculate() {return indicesCalculation.getIndxes();}

    @PostMapping("/nemesys/scrapping")
    public List<NematodeGenus> scrappingNemaplex() {
        return scrapingService.updateNematodesList();
    }

}
