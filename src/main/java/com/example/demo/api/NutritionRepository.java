package com.example.demo.api;

import java.util.List;

import com.example.demo.models.CalorieDTO;
import com.example.demo.models.NutritionProduct;
import com.example.demo.models.NutritionResponse;

// Database interface
public interface NutritionRepository {
    List<NutritionProduct>find(String prod_name);
    List<CalorieDTO> findCalories_DTO(String prod_name);
    boolean addEntry(NutritionResponse entry);
}