package com.example.demo.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class NutritionRequest {
    @NotBlank // Not empty
    private String resource;
    
    @Positive // Not negative
    private double amount;

    //@NotBlank -> Only works on string
    private Unit unit; // grams, decilitre, cups...


    public NutritionRequest(){

    }

    
    public String getResource(){
        return this.resource;
    }
    
    public double getAmount(){
        return this.amount;
    }
    public Unit getUnit(){
        return this.unit;
    }


    public void setResource(String resource){
        this.resource = resource;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setUnit(Unit unit){
        this.unit = unit;
    }
    


}

