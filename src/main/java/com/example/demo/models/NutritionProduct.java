package com.example.demo.models;

import java.util.function.Consumer;

// DTO: Data Transfer Object
// its only job is to carry data
// Double = nullable (wrapper for double)
public class NutritionProduct {

        private int id;
        private String product_name;
        private Double energy_kcal_100g;
        private Double fat_100g;
        private Double saturated_fat_100g;
        private Double trans_fat_100g;
        private Double cholesterol_100g;
        private Double carbohydrates_100g;
        private Double sugars_100g;
        private Double added_sugars_100g;
        private Double sucrose_100g;
        private Double glucose_100g;
        private Double fructose_100g;
        private Double lactose_100g;
        private Double starch_100g;
        private Double fiber_100g;
        private Double proteins_100g;
        private Double salt_100g;
        private Double added_salt_100g;
        private Double sodium_100g;
        private Double vitamin_c_100g;
        private Double vitamin_b1_100g;
        private Double vitamin_b2_100g;
        private Double vitamin_pp_100g;
        private Double vitamin_b6_100g;
        private Double vitamin_b9_100g;
        private Double vitamin_b12_100g;
        private Double potassium_100g;
        private Double calcium_100g;
        private Double iron_100g;
        private Double magnesium_100g;
        private Double zinc_100g;

    public NutritionProduct(){}

    public static <T> void setField(T value, Consumer<T> setter) {
        setter.accept(value);
    }




    public void setId(int id){
        this.id = id;
    }
    public void setName (String name) {
        this.product_name = name;
    }   
    public void setCalories(Double calories) {
        this.energy_kcal_100g = calories;
    } 
    public void setFat(Double fat) {
        this.fat_100g = fat;
    }
    public void setSatFat(Double saturated_fat) {
        this.saturated_fat_100g = saturated_fat; 
    }
    public void setTransFat(Double trans_fat) {
        this.trans_fat_100g = trans_fat; 
    }
    public void setCholesterol(Double cholesterol) {
        this.cholesterol_100g = cholesterol;
    }
    public void setCarbs(Double carbs) {
        this.carbohydrates_100g = carbs;
    }
    public void setSugars(Double sugars) {
        this.sugars_100g = sugars; 
    }
    public void setAddedSugars(Double added_sugars) {
        this.added_sugars_100g = added_sugars;
    }
    public void setSucrose(Double sucrose) {
        this.sucrose_100g = sucrose;
    }
    public void setGlucose(Double glucose) {
        this.glucose_100g = glucose;
    }
    public void setFructose(Double fructose) {
        this.fructose_100g = fructose;
    }
    public void setLactose(Double lactose) {
        this.lactose_100g = lactose;
    }
    public void setStarch(Double starch) {
        this.starch_100g = starch;
    }
    public void setFiber(Double fiber){
        this.fiber_100g = fiber;
     }
    public void setProtein(Double proteins) {
        this.proteins_100g = proteins;
    }
    public void setSalt(Double salt) {
        this.salt_100g = salt;
    }
    public void setAddedSalt(Double added_salt) {
        this.added_salt_100g  = added_salt;
    }
    public void setSodium(Double sodium){
        this.sodium_100g  = sodium;
    }
    public void setVitaminC(Double vitamin_c) {
        this.vitamin_c_100g  = vitamin_c;
    }
    public void setVitaminB1(Double vitamin_b1) {
        this.vitamin_b1_100g = vitamin_b1;
    }
    public void setVitaminB2(Double vitamin_b2) {
        this.vitamin_b2_100g = vitamin_b2;
    }
    public void setVitaminPP(Double vitamin_pp) {
        this.vitamin_pp_100g = vitamin_pp;
    }
    public void setVitaminB6(Double vitamin_b6) {
        this.vitamin_b6_100g = vitamin_b6;
    }
    public void setVitaminB9(Double vitamin_b9) {
        this.vitamin_b9_100g = vitamin_b9;
    }
    public void setVitaminB12(Double vitamin_b12) {
        this.vitamin_b12_100g = vitamin_b12;
    }
    public void setPotassium(Double potassium){
        this.potassium_100g = potassium;
    }
    public void setCalcium(Double calcium) {
        this.calcium_100g = calcium;
    }
    public void setIron(Double iron) {
        this.iron_100g = iron;
    }
    public void setMagnesium(Double magnesium) {
        this.magnesium_100g = magnesium;
    }
    public void setZinc(Double zinc) {
        this.zinc_100g = zinc;
    }



    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.product_name;
    }
    public Double getEnergy_kcal(){
        return this.energy_kcal_100g;
    }
    public Double getFat(){
        return this.fat_100g;
    }
    public Double getSaturated_fat(){
        return this.saturated_fat_100g;
    }
    public Double getTrans_fat(){
        return this.trans_fat_100g;
    }
    public Double getCholesterol(){
        return this.cholesterol_100g;
    }
    public Double getCarbohydrates(){
        return this.carbohydrates_100g;
    }
    public Double getSugars(){
        return this.sugars_100g;
    }
    public Double getAdded_sugars(){
        return this.added_sugars_100g;
    }
    public Double getSucrose(){
        return this.sucrose_100g;
    }
    public Double getGlucose(){
        return this.glucose_100g;
    }
    public Double getFructose(){
        return this.fructose_100g;
    }
    public Double getLactose(){
        return this.lactose_100g;
    }
    public Double getStarch(){
        return this.starch_100g;
    }
    public Double getFiber(){
        return this.fiber_100g;
    }
    public Double getProteins(){
        return this.proteins_100g;
    }
    public Double getSalt(){
        return this.salt_100g;
    }
    public Double getAdded_salt(){
        return this.added_salt_100g;
    }
    public Double getSodium(){
        return this.sodium_100g;
    }
    public Double getVitamin_c(){
        return this.vitamin_c_100g;
    }
    public Double getVitamin_b1(){
        return this.vitamin_b1_100g;
    }
    public Double getVitamin_b2(){
        return this.vitamin_b2_100g;
    }
    public Double getVitamin_pp(){
        return this.vitamin_pp_100g;
    }
    public Double getVitamin_b6(){
        return this.vitamin_b6_100g;
    }
    public Double getVitamin_b9(){
        return this.vitamin_b9_100g;
    }
    public Double getVitamin_b12(){
        return this.vitamin_b12_100g;
    }
    public Double getPotassium(){
        return this.potassium_100g;
    }
    public Double getCalcium(){
        return this.calcium_100g;
    }
    public Double getIron(){
        return this.iron_100g;
    }
    public Double getMagnesium(){
        return this.magnesium_100g;
    }
    public Double getZinc(){
        return this.zinc_100g;
    }
}