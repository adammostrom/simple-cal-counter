package com.example.demo.models;

public class NutritionRequest {
    private String resource;
    private Double amount;
    private String unit; // grams, decilitre, cups...


    public NutritionRequest(){

    }

    public String GetResource(){
        return this.resource;
    }
    public Double GetAmount(){
        return this.amount;
    }
    public String GetUnit(){
        return this.unit;
    }


    public void SetResource(String resource){
        this.resource = resource;
    }
    public void SetAmount(Double amount){
        this.amount = amount;
    }
    public void SetUnit(String unit){
        this.unit = unit;
    }
    


}
