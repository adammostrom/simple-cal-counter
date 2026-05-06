package com.example.demo.controller;

import com.example.demo.models.NutritionRequest;
import com.example.demo.models.NutritionResponse;
import com.example.demo.service.NutritionService;
import org.springframework.web.bind.annotation.*;

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

    /*
    RequestBody = Converts JSON from request body into java object (automagically)
    Spring converts JSON to NutritionRequest
    Controller method runs, service calculates
    the controller then returns the NutritionResponse
    Spring converts to JSON

    Why is it POST? -> Because user sends data in request body
    GET request is for "give me a resource" when data is in the URL,
        - Should be read only 
        - Should be cachable

    POST = Process this data (data is in the body)

    for a GET it would be something like:
    curl "http://localhost:8080/nutrition?ingredient=apple&amount=200&unit=g"
    
    Use GET if:

        - simple queries
        - no side effects
        - easily cacheable
        - bookmarkable


    Use POST if:

        - complex input
        - JSON objects
        - validation-heavy input
        - future extensibility
    */ 
    @PostMapping // HTTP POST Request
    public NutritionResponse getNutrition(@Valid @RequestBody NutritionRequest request) {
        return service.calculate(
            request.getResource(),
            request.getAmount(),
            request.getUnit()
        );
    }
}

