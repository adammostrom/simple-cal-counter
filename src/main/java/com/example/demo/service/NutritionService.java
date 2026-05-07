package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.api.NutritionRepository;
import com.example.demo.models.NutritionResponse;
import com.example.demo.models.Unit;

@Service
public class NutritionService {



    private NutritionRepository repo;

    public NutritionService(NutritionRepository repo) {
        this.repo = repo;
    }

    public NutritionResponse get(String name) {
        return repo.find(name);
    }



    public NutritionResponse calculate(String resource, double amount, Unit unit) {

        // Validate resource
        
        // fetch resource (like oats)

        // return nutritional value


        NutritionResponse resp = get(resource);


        double grams = convertToGrams(amount, unit);

        double factor = grams / 100.0;

        // TODO: Handle this factor here? Or just return the fetched Nutrition Response, alternatively decide how to convert between units
        return new NutritionResponse(
                resource,
                resp.getCalories() * factor,
                resp.getProtein() * factor,
                resp.getCarbs() * factor,
                resp.getFat() * factor
        );
    }

    private double convertToGrams(double amount, Unit unit) {
        switch (unit) {
            case GRAM: return amount;
            case DL: return amount * 100;   // rough assumption, based on density, viscosity etc. Add functions for this
            case CUPS: return amount * 240; // rough
            default: throw new IllegalArgumentException("Unknown unit");
        }
    }

}