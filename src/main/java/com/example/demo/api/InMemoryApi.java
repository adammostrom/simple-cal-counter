package com.example.demo.api;

import java.util.ArrayList;

import com.example.demo.models.NutritionResponse;

public class InMemoryApi implements NutritionRepository{

    public ArrayList<NutritionResponse> databank = new ArrayList<>();

    NutritionResponse oats = new NutritionResponse("oats", 369.56, 14, 60, 7);
    NutritionResponse rice = new NutritionResponse("rice", 350, 7, 78, 1);

    public InMemoryApi(){
        databank.add(oats);
        databank.add(rice);
    }


    public boolean addEntry(NutritionResponse entry){
        return databank.add(entry);
    }

    public NutritionResponse find(String resource){
        for (NutritionResponse item : databank){
            if (item.getResouce().equalsIgnoreCase(resource)){
                return item;
            }
        }
          throw new IllegalArgumentException("Unknown resource: " + resource);
    }   
}