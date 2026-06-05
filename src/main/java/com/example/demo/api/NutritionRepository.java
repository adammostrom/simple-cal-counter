package com.example.demo.api;

import java.util.List;

import com.example.demo.models.NutritionProduct;

// Database interface
public interface NutritionRepository {

    List<NutritionProduct>findMedian(String prod_name);
    List<NutritionProduct>findRaw(String prod_name);

    boolean addEntry(NutritionProduct entry, String table);
}