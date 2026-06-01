package com.example.demo.models;

import java.util.Map;

public record NutritionResponse(

    String name,
    Map<Field, Double> fields
){}
    