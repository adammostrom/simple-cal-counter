package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.NutritionResponse;
import com.example.demo.models.ProductRequest;
import com.example.demo.service.NutritionService;

/*
Any request to /nutrition comes here.
*/ 
@RestController
@RequestMapping("/nutrition")
public class NutritionController {
    
    private final NutritionService service;

    public NutritionController(NutritionService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<NutritionResponse> getNutrition( @RequestBody ProductRequest request) {

    NutritionResponse response = service.get(request);


    return ResponseEntity.ok(response);
}

}

