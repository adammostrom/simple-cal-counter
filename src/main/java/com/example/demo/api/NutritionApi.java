package com.example.demo.api;

import java.util.ArrayList;

import com.example.demo.models.NutritionResponse;;

public class NutritionApi {


    public NutritionApi(){

    }

/*
        this.resource = resource;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;

*/
    public ArrayList<NutritionResponse> databank = new ArrayList<>();

    // Always per 100g for easy calculation, protein and carbs are in percentage
    NutritionResponse oats = new NutritionResponse("oats", 369.56, 14, 60, 7);


    public boolean addToDatabank(NutritionResponse entry){
        return databank.add(entry);
    }

    public NutritionResponse getFromDatabank(String resource){
        if (databank.contains(resource.toLowerCase())) {
            int index = databank.indexOf(resource.toLowerCase());
            return databank.get(index);
        } else {
            throw new IllegalArgumentException("Unknown resource");
        }
    }
}