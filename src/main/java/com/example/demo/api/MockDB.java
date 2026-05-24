package com.example.demo.api;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.CalorieDTO;
import com.example.demo.models.NutritionProduct;
import com.example.demo.models.NutritionResponse;


public class MockDB implements NutritionRepository{

    public ArrayList<NutritionResponse> databank = new ArrayList<>();
/* 
    NutritionResponse oats = new NutritionResponse("oats", 369.56, 14, 60, 7);
    NutritionResponse rice = new NutritionResponse("rice", 350, 7, 78, 1);
    NutritionResponse apple = new NutritionResponse("apple",52, 14, 14, 0.2); */
        

    public MockDB(){

    }

    public List<CalorieDTO> findCalories_DTO(String prod_name){
        return null;
    }


    public boolean addEntry(NutritionResponse entry){
        return databank.add(entry);
    }

    public List <NutritionProduct> find(String resource){

        // Check cache first



        for (NutritionResponse item : databank){
            if (item.getResource().equalsIgnoreCase(resource)){
                return null;
            }
        }
        return null; // Maybe come back to change this later
    }
}