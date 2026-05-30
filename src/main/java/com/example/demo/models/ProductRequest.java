package com.example.demo.models;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
    
    // Name of the producft
    @NotEmpty
    String product_name,
   
    // Amount of grams per 100g
    @Positive
    Double amount,

    // Grams, Cups etc
    Unit unit,

    // Product structure 
    Set<Field>fields

){}