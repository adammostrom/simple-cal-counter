package com.example.demo.api;

import com.example.demo.models.NutritionResponse;

// Database interface
public interface NutritionRepository {
    NutritionResponse find(String name);
    boolean addEntry(NutritionResponse entry);
}