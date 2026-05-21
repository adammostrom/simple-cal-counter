package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.api.NutritionApi;
import com.example.demo.api.NutritionRepository;
import com.example.demo.models.NutritionResponse;
import com.example.demo.models.Unit;

@Service
public class NutritionService {


    //private NutritionCache cache;

    private NutritionRepository repo;
    private NutritionApi api;
    private NutritionCache cache;

    public NutritionService(NutritionRepository repo, NutritionCache cache, NutritionApi api) {
        this.repo = repo;
        this.cache = cache;
        this.api = api;
    }

    public NutritionResponse get(String raw, double amount, Unit unit) {

        try {
            NutritionResponse response = fetchAndStore(raw);

            return calculate(response, amount, unit);
        } catch (Exception e) {
            throw new IllegalArgumentException("Item not found: " + raw);
        }
    }

    public NutritionResponse fetchAndStore(String raw_name) {
        
        // NOrmalize name first: clean up etc.

        String name = normalize(raw_name);

        NutritionResponse nutrition = cache.fetch(name);

        // Found in cache. Return early
        if ( nutrition != null){
            return nutrition;
        }
        
        // Not found in cahce, check db
        nutrition = repo.find(name);
        
        if (nutrition != null) {
            cache.store(nutrition);
            return nutrition;
        }
    
        // Check API.
        nutrition = api.fetch(name);
        if (nutrition != null) {
            cache.store(nutrition);
            repo.addEntry(nutrition);        
            return nutrition;
        }
        throw new IllegalArgumentException(name + " not found");        
    }

    private String normalize(String name){
        // Add more eventually
        return name.toLowerCase().trim();
    }


    public NutritionResponse calculate(NutritionResponse nutrition, double amount, Unit unit) {

        // Validate resource
        
        // fetch resource (like oats)

        // return nutritional value

        double grams = convertToGrams(amount, unit);

        double factor = grams / 100.0;

        // TODO: Handle this factor here? Or just return the fetched Nutrition Response, alternatively decide how to convert between units
        return new NutritionResponse(
                nutrition.getResource(),
                nutrition.getCalories() * factor,
                nutrition.getProtein() * factor,
                nutrition.getCarbs() * factor,
                nutrition.getFat() * factor
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