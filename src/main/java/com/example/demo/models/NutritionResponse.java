package com.example.demo.models;


public class NutritionResponse {
    private String resource;
    private double calories;
    private double protein;
    private double carbohydrates;
    private double fat;
    // Follow the nutrition table (standardized) -> per 100g



    public NutritionResponse(String ingredient, double calories, double protein, double carbs, double fat) {
        this.ingredient = ingredient;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

}