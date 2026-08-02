package com.example.demo.models;

import java.util.function.Function;

public enum Field {
    CALORIES("energy_kcal", NutritionProduct::getEnergy_kcal),
    FAT("fat", NutritionProduct::getFat),
    SATURATED_FAT("saturated_fat", NutritionProduct::getSaturated_fat),
    TRANS_FAT("trans_fat", NutritionProduct::getTrans_fat),
    CHOLESTEROL("cholesterol", NutritionProduct::getCholesterol),
    CARBOHYDRATES("carbohydrates", NutritionProduct::getCarbohydrates),
    SUGARS("sugars", NutritionProduct::getSugars),
    ADDED_SUGARS("added_sugars", NutritionProduct::getAdded_sugars),
    SUCROSE("sucrose", NutritionProduct::getSucrose),
    GLUCOSE("glucose", NutritionProduct::getGlucose),
    FRUCTOSE("fructose", NutritionProduct::getFructose),
    LACTOSE("lactose", NutritionProduct::getLactose),
    STARCH("starch", NutritionProduct::getStarch),
    FIBER("fiber", NutritionProduct::getFiber),
    PROTEINS("proteins", NutritionProduct::getProteins),
    SALT("salt", NutritionProduct::getSalt),
    ADDED_SALT("added_salt", NutritionProduct::getAdded_salt),
    SODIUM("sodium", NutritionProduct::getSodium),
    VITAMIN_C("vitamin_c", NutritionProduct::getVitamin_c),
    VITAMIN_B1("vitamin_b1", NutritionProduct::getVitamin_b1),
    VITAMIN_B2("vitamin_b2", NutritionProduct::getVitamin_b2),
    VITAMIN_PP("vitamin_pp", NutritionProduct::getVitamin_pp),
    VITAMIN_B6("vitamin_b6", NutritionProduct::getVitamin_b6),
    VITAMIN_B9("vitamin_b9", NutritionProduct::getVitamin_b9),
    VITAMIN_B12("vitamin_b12", NutritionProduct::getVitamin_b12),
    POTASSIUM("potassium", NutritionProduct::getPotassium),
    CALCIUM("calcium", NutritionProduct::getCalcium),
    IRON("iron", NutritionProduct::getIron),
    MAGNESIUM("magnesium", NutritionProduct::getMagnesium),
    ZINC("zinc", NutritionProduct::getZinc);



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


    public boolean doesFieldExist(String f){
        for(Field field : Field.values()){
            if(field.getDbColumn().equals(f)){
                return true;
            }
        }
        return false;
    }

    public int numberOfFields(){
        int count = 0;
        for(Field f : Field.values()){
            count++;
        }
        return count;
    }
}

