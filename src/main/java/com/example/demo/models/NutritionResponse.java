package com.example.demo.models;


public class NutritionResponse {
    private String resource;
    private double calories;
    private double protein;
    private double carbs;
    private double fat;
    // Add: Fat (saturated, Trans), Cholesterol, Sodium, carbs(Dietary Fiber, Sugars) added sugars, Vitamin, Calcium, Iron, Potassium
    // Follow the nutrition table (standardized) -> per 100g



    public NutritionResponse(String resource, double calories, double protein, double carbs, double fat) {
        this.resource = resource;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public String getResouce() {
        return this.resource;
    }
    public double getCalories() {
        return this.calories;
    }
    public double getProtein() {
        return this.protein;
    }
    public double getCarbs(){
        return this.carbs;
    }
    public double getFat(){
        return this.fat;
    }

    public void setResouce(String resource) {
        this.resource = resource;
    }
    public void setCalories(double calories) {
        this.calories = calories;
    }
    public void setProtein(double protein) {
        this.protein = protein;
    }
    public void setCarbs(double carbs){
        this.carbs = carbs;
    }
    public void setFat(double fat){
        this.fat = fat;
    }


}