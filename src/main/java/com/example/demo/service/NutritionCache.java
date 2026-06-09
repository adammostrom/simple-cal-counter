package com.example.demo.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.demo.models.NutritionProduct;

@Component
public class NutritionCache {



    private Map<String, NutritionProduct> cache = new ConcurrentHashMap<>();


    public NutritionCache(){

    }
    

    public Optional <NutritionProduct> fetch(String name){
        if(!cache.containsKey(name)){
            return Optional.empty();
        }
        //System.out.printf("Cache returned. Name: %s. ID: %d \n", name, nutrition.getId(), nutrition);
        return Optional.of(cache.get(name)); 
    }

    public void store(NutritionProduct nutrition) {
        cache.put(nutrition.getName(), nutrition);

        if (cache.containsKey(nutrition.getName())){
            System.out.printf("Cache Stored. Name: %s. ID: %d \n", nutrition.getName(), nutrition.getId(), nutrition);

        } else {
            System.out.printf("Failed to store %s with id %d in cache.\n", nutrition.getName(), nutrition.getId());
        }
        return;
    }
}
