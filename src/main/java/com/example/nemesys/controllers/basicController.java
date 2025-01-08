package com.example.nemesys.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class basicController {

    @GetMapping("/nemesys")
    public String indexPage() {
        return "Hello, World!";
    }

    @GetMapping("/nemesys/calculate")
    public String indicesCalculate() {return "Hello from calculator";}

}
