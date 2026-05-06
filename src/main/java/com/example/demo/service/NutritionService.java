package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.models.NutritionResponse;
import com.example.demo.models.Unit;

@Service
public class NutritionService {

    public NutritionResponse calculate(String resource, double amount, String unit) {

        // Validate resource
        
        // fetch resource (like oats)

        // return nutritional value
        // fake data per 100g
        double caloriesPer100g = 52; // apple
        double proteinPer100g = 0.3;
        double carbsPer100g = 14;
        double fatPer100g = 0.2;

        double grams = convertToGrams(amount, unit);

        double factor = grams / 100.0;

        return new NutritionResponse(
                resource,
                caloriesPer100g * factor,
                proteinPer100g * factor,
                carbsPer100g * factor,
                fatPer100g * factor
        );
    }

    private double convertToGrams(double amount, Unit unit) {
        switch (unit.toLowerCase()) {
            case GRAM: return amount;
            case DCL: return amount * 100;   // rough assumption
            case CUPS: return amount * 240; // rough
            default: throw new IllegalArgumentException("Unknown unit");
        }
    }
}