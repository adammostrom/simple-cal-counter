package com.example.demo.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.example.demo.models.NutritionResponse;

@Component
public class NutritionCache {



    private Map<String, NutritionResponse> cache = new ConcurrentHashMap<>();


    public NutritionCache(){

    }
    

    public NutritionResponse fetch(String name){
        if(!cache.containsKey(name)){
            return null;
            //throw new IllegalArgumentException("name not found: " +  name);
        }
        return cache.get(name);
    }

    public void store(NutritionResponse nutrition) {
        cache.put(nutrition.getResource(), nutrition);
    }
}
