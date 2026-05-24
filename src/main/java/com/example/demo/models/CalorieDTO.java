package com.example.demo.models;

public class CalorieDTO {

    private String product_name;
    private Double energy_kcal_100g;

    public CalorieDTO(String product_name, Double calories){
        this.product_name = product_name;
        this.energy_kcal_100g = calories;
    }

    public String getName(){
        return this.product_name;
    }
    public Double getCalories(){
        return this.energy_kcal_100g;
    }
    
}
