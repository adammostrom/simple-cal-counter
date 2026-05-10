package com.example.demo.api;

import org.springframework.stereotype.Component;

import com.example.demo.models.NutritionResponse;

@Component
public class ExternalAPI implements NutritionApi {

    @Override
    public NutritionResponse fetch(String name) {
        // call your external API / DB / mock
        return null;
    }
}