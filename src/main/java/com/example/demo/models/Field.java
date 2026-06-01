package com.example.demo.models;

import java.util.function.Function;

public enum Field {
    CALORIES("energy_kcal_100g", NutritionProduct::getEnergy_kcal),
    FAT("fat_100g", NutritionProduct::getFat),
    SATURATED_FAT("saturated_fat_100g", NutritionProduct::getSaturated_fat),
    TRANS_FAT("trans_fat_100g", NutritionProduct::getTrans_fat),
    CHOLESTEROL("cholesterol_100g", NutritionProduct::getCholesterol),
    CARBOHYDRATES("carbohydrates_100g", NutritionProduct::getCarbohydrates),
    SUGARS("sugars_100g", NutritionProduct::getSugars),
    ADDED_SUGARS("added_sugars_100g", NutritionProduct::getAdded_sugars),
    SUCROSE("sucrose_100g", NutritionProduct::getSucrose),
    GLUCOSE("glucose_100g", NutritionProduct::getGlucose),
    FRUCTOSE("fructose_100g", NutritionProduct::getFructose),
    LACTOSE("lactose_100g", NutritionProduct::getLactose),
    STARCH("starch_100g", NutritionProduct::getStarch),
    FIBER("fiber_100g", NutritionProduct::getFiber),
    PROTEINS("proteins_100g", NutritionProduct::getProteins),
    SALT("salt_100g", NutritionProduct::getSalt),
    ADDED_SALT("added_salt_100g", NutritionProduct::getAdded_salt),
    SODIUM("sodium_100g", NutritionProduct::getSodium),
    VITAMIN_C("vitamin_c_100g", NutritionProduct::getVitamin_c),
    VITAMIN_B1("vitamin_b1_100g", NutritionProduct::getVitamin_b1),
    VITAMIN_B2("vitamin_b2_100g", NutritionProduct::getVitamin_b2),
    VITAMIN_PP("vitamin_pp_100g", NutritionProduct::getVitamin_pp),
    VITAMIN_B6("vitamin_b6_100g", NutritionProduct::getVitamin_b6),
    VITAMIN_B9("vitamin_b9_100g", NutritionProduct::getVitamin_b9),
    VITAMIN_B12("vitamin_b12_100g", NutritionProduct::getVitamin_b12),
    POTASSIUM("potassium_100g", NutritionProduct::getPotassium),
    CALCIUM("calcium_100g", NutritionProduct::getCalcium),
    IRON("iron_100g", NutritionProduct::getIron),
    MAGNESIUM("magnesium_100g", NutritionProduct::getMagnesium),
    ZINC("zinc_100g", NutritionProduct::getZinc);



    private final String db_col;
    private final Function<NutritionProduct, Double> getter;

    Field(String db_col, Function<NutritionProduct, Double> getter){
        this.db_col = db_col;
        this.getter = getter;
    }

    public String getDbColumn(){
        return db_col;
    }

    public Double get(NutritionProduct p){
        return getter.apply(p);
    }
}


/*
  product_name
  energy_kcal_100
  fat_100
  saturated_fat_100g
  trans_fat_100g
  cholesterol_100g
  carbohydrates_100g
  sugars_100g
  added_sugars_100g
  sucrose_100g
  glucose_100g
  fructose_100g
  lactose_100g
  starch_100g
  fiber_100g
  proteins_100g
  salt_100g
  added_salt_100g
  sodium_100g
  vitamin_c_100g
  vitamin_b1_100g
  vitamin_b2_100g
  vitamin_pp_100g
  vitamin_b6_100g
  vitamin_b9_100g
  vitamin_b12_100g
  potassium_100g
  calcium_100g
  iron_100g
  magnesium_100g
  zinc_100g

*/