package com.example.demo.api;

import org.springframework.stereotype.Component;

import com.example.demo.models.NutritionResponse;

@Component
public class ExternalAPI implements NutritionApi {


    // TODO
    @Override
    public NutritionResponse fetch(String name) {
        return null;
    }
}