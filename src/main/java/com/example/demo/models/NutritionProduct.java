package com.example.demo.models;

import java.util.function.Consumer;

// DTO: Data Transfer Object
// its only job is to carry data
// Double = nullable (wrapper for double)

/*
TODO: Could be good to transform into map structure:

name:
Map<String, Double> fields;

*/
public class NutritionProduct {

        private int id;
        private String product_name;
        private Double energy_kcal;
        private Double fat;
        private Double saturated_fat;
        private Double trans_fat;
        private Double cholesterol;
        private Double carbohydrates;
        private Double sugars;
        private Double added_sugars;
        private Double sucrose;
        private Double glucose;
        private Double fructose;
        private Double lactose;
        private Double starch;
        private Double fiber;
        private Double proteins;
        private Double salt;
        private Double added_salt;
        private Double sodium;
        private Double vitamin_c;
        private Double vitamin_b1;
        private Double vitamin_b2;
        private Double vitamin_pp;
        private Double vitamin_b6;
        private Double vitamin_b9;
        private Double vitamin_b12;
        private Double potassium;
        private Double calcium;
        private Double iron;
        private Double magnesium;
        private Double zinc;

    public NutritionProduct(){}

    public static <T> void setField(T value, Consumer<T> setter) {
        setter.accept(value);
    }

    // Not the most elegant solution but w/e
    public int countNullFields() {
        int count = 0;

        if (product_name == null) count++;
        if (energy_kcal == null) count++;
        if (fat == null) count++;
        if (saturated_fat == null) count++;
        if (trans_fat == null) count++;
        if (cholesterol == null) count++;
        if (carbohydrates == null) count++;
        
        if (sugars == null) count++;
        if (added_sugars == null) count++;
        if (sucrose == null) count++;
        if (glucose == null) count++;
        if (fructose == null) count++;
        if (lactose == null) count++;

        if (starch == null) count++;  
        if (fiber == null) count++;
        if (proteins == null) count++;
        if (salt == null) count++;
        if (added_salt == null) count++;
        if (sodium == null) count++;
        if (vitamin_c == null) count++;
        
        if (vitamin_b1 == null) count++;  
        if (vitamin_b2 == null) count++;
        if (vitamin_pp == null) count++;
        if (vitamin_b6 == null) count++;
        if (vitamin_b9 == null) count++;
        if (vitamin_b12 == null) count++;
        if (potassium == null) count++;
        if (calcium == null) count++;

        if (iron == null) count++;
        if (magnesium == null) count++;
        if (zinc == null) count++;

    return count;
}


    
    public String returnAsCommaSeparatedString(){
        String data = getName();
        for (Field f : Field.values()){
            data += (f.get(this) == null) ? ",null" : "," + f.get(this).toString();
        }
        return data + "\n";
    }


    public void setId(int id){
        this.id = id;
    }
    public void setName (String name) {
        this.product_name = name;
    }   
    public void setCalories(Double calories) {
        this.energy_kcal = calories;
    } 
    public void setFat(Double fat) {
        this.fat = fat;
    }
    public void setSatFat(Double saturated_fat) {
        this.saturated_fat = saturated_fat; 
    }
    public void setTransFat(Double trans_fat) {
        this.trans_fat = trans_fat; 
    }
    public void setCholesterol(Double cholesterol) {
        this.cholesterol = cholesterol;
    }
    public void setCarbs(Double carbs) {
        this.carbohydrates = carbs;
    }
    public void setSugars(Double sugars) {
        this.sugars = sugars; 
    }
    public void setAddedSugars(Double added_sugars) {
        this.added_sugars = added_sugars;
    }
    public void setSucrose(Double sucrose) {
        this.sucrose = sucrose;
    }
    public void setGlucose(Double glucose) {
        this.glucose = glucose;
    }
    public void setFructose(Double fructose) {
        this.fructose = fructose;
    }
    public void setLactose(Double lactose) {
        this.lactose = lactose;
    }
    public void setStarch(Double starch) {
        this.starch = starch;
    }
    public void setFiber(Double fiber){
        this.fiber = fiber;
     }
    public void setProtein(Double proteins) {
        this.proteins = proteins;
    }
    public void setSalt(Double salt) {
        this.salt = salt;
    }
    public void setAddedSalt(Double added_salt) {
        this.added_salt  = added_salt;
    }
    public void setSodium(Double sodium){
        this.sodium  = sodium;
    }
    public void setVitaminC(Double vitamin_c) {
        this.vitamin_c  = vitamin_c;
    }
    public void setVitaminB1(Double vitamin_b1) {
        this.vitamin_b1 = vitamin_b1;
    }
    public void setVitaminB2(Double vitamin_b2) {
        this.vitamin_b2 = vitamin_b2;
    }
    public void setVitaminPP(Double vitamin_pp) {
        this.vitamin_pp = vitamin_pp;
    }
    public void setVitaminB6(Double vitamin_b6) {
        this.vitamin_b6 = vitamin_b6;
    }
    public void setVitaminB9(Double vitamin_b9) {
        this.vitamin_b9 = vitamin_b9;
    }
    public void setVitaminB12(Double vitamin_b12) {
        this.vitamin_b12 = vitamin_b12;
    }
    public void setPotassium(Double potassium){
        this.potassium = potassium;
    }
    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }
    public void setIron(Double iron) {
        this.iron = iron;
    }
    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }
    public void setZinc(Double zinc) {
        this.zinc = zinc;
    }



    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.product_name;
    }
    public Double getEnergy_kcal(){
        return this.energy_kcal;
    }
    public Double getFat(){
        return this.fat;
    }
    public Double getSaturated_fat(){
        return this.saturated_fat;
    }
    public Double getTrans_fat(){
        return this.trans_fat;
    }
    public Double getCholesterol(){
        return this.cholesterol;
    }
    public Double getCarbohydrates(){
        return this.carbohydrates;
    }
    public Double getSugars(){
        return this.sugars;
    }
    public Double getAdded_sugars(){
        return this.added_sugars;
    }
    public Double getSucrose(){
        return this.sucrose;
    }
    public Double getGlucose(){
        return this.glucose;
    }
    public Double getFructose(){
        return this.fructose;
    }
    public Double getLactose(){
        return this.lactose;
    }
    public Double getStarch(){
        return this.starch;
    }
    public Double getFiber(){
        return this.fiber;
    }
    public Double getProteins(){
        return this.proteins;
    }
    public Double getSalt(){
        return this.salt;
    }
    public Double getAdded_salt(){
        return this.added_salt;
    }
    public Double getSodium(){
        return this.sodium;
    }
    public Double getVitamin_c(){
        return this.vitamin_c;
    }
    public Double getVitamin_b1(){
        return this.vitamin_b1;
    }
    public Double getVitamin_b2(){
        return this.vitamin_b2;
    }
    public Double getVitamin_pp(){
        return this.vitamin_pp;
    }
    public Double getVitamin_b6(){
        return this.vitamin_b6;
    }
    public Double getVitamin_b9(){
        return this.vitamin_b9;
    }
    public Double getVitamin_b12(){
        return this.vitamin_b12;
    }
    public Double getPotassium(){
        return this.potassium;
    }
    public Double getCalcium(){
        return this.calcium;
    }
    public Double getIron(){
        return this.iron;
    }
    public Double getMagnesium(){
        return this.magnesium;
    }
    public Double getZinc(){
        return this.zinc;
    }
}