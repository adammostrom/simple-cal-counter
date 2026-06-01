package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.demo.models.NutritionProduct;

@Component
public class NutritionCache {



    private Map<String, NutritionProduct> cache = new ConcurrentHashMap<>();


    public NutritionCache(){

    }
    

    public NutritionProduct fetch(String name){
        if(!cache.containsKey(name)){
            return null;
            //throw new IllegalArgumentException("name not found: " +  name);
        }
        NutritionProduct nutrition = cache.get(name);
        System.out.printf("Cache returned. Name: %s. ID: %d \n", name, nutrition.getId(), nutrition);
        return nutrition;
    }

    public void store(NutritionProduct nutrition) {
        cache.put(nutrition.getName(), nutrition);

        if (cache.containsKey(nutrition.getName())){
            System.out.printf("Cache Stored. Name: %s. ID: %d \n", nutrition.getName(), nutrition.getId(), nutrition);

        }
    }
}
