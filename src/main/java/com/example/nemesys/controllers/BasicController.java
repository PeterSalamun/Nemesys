package com.example.nemesys.controllers;

import com.example.nemesys.services.IndicesCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicController {

    @Autowired
    private IndicesCalculation indicesCalculation;

    @GetMapping("/nemesys")
    public String indexPage() {
        return "Hello, World!";
    }

    @GetMapping("/nemesys/calculate")
    public String indicesCalculate() {return indicesCalculation.getIndxes();}

}
