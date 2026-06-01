package com.example.demo.api;

import java.util.List;

import com.example.demo.models.NutritionProduct;

// Database interface
public interface NutritionRepository {
    List<NutritionProduct>find(String prod_name, String table);
    boolean addEntry(NutritionProduct entry, String table);
}