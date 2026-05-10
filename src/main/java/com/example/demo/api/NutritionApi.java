package com.example.demo.api;

import com.example.demo.models.NutritionResponse;

public interface NutritionApi {
    NutritionResponse fetch(String name);
}
