package com.example.demo.service;

import static java.nio.file.StandardOpenOption.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.api.NutritionApi;
import com.example.demo.api.NutritionRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.models.Field;
import com.example.demo.models.NutritionProduct;
import com.example.demo.models.NutritionResponse;
import com.example.demo.models.Unit;


@Service
public class NutritionService {


    private static final Logger log = LoggerFactory.getLogger(NutritionService.class);


    private NutritionRepository repo;
    private NutritionApi api;
    private NutritionCache cache;


    public NutritionService(NutritionRepository repo, NutritionCache cache, NutritionApi api) {
        this.repo = repo;
        this.cache = cache;
        this.api = api;
    }


    // TODO: Maybe add nutritionrequest here instead (POST request) with fields to get
    public NutritionResponse get(String raw, double amount, Unit unit, Set<Field> fields) {

        // Add validation for request?

        String name = normalize(raw);
        try {
            
            Optional<NutritionProduct> nutrition = cache.fetch(normalize(name));
            if (!nutrition.isPresent()) {
                nutrition = geNutritionProductFromRepo(normalize(raw));
                if (!nutrition.isPresent()){
                    log.warn("Item not found " + raw);
                    throw new IllegalArgumentException("Item not found: " + raw);
                } else {
                    // Set the name to lower case so we will match in the cache
                    nutrition.get().setName(normalize(nutrition.get().getName()));
                    cache.store(nutrition.get());

                }
            }
            
        // Compute amount and unit before converting to response
        return convertToResponse(nutrition.get(), fields, amount);

        } catch (Exception e) {
            throw e;
        }
    }


    public Optional<NutritionProduct> geNutritionProductFromRepo(String raw_name) {
        
        List <NutritionProduct> nutritions = repo.findMedian(raw_name);
        if (!nutritions.isEmpty()){
            if (nutritions.size() > 1){
                log.warn("Multiple median entries found for {}", raw_name);
                // TODO, handle this, recalculate median? Or just get the first one since its alreay been calculated?
            }
            log.debug("Found median-computed entry count={}", nutritions.size());
            // The median table SHOULD only return one value. TODO handle this better
            return Optional.of(nutritions.get(0));
        }
        nutritions = repo.findRaw(raw_name);
        if (nutritions.isEmpty()) {
            throw new ResourceNotFoundException(raw_name + " not found.\n");
        }
        log.info("Found {} raw rows for {}", nutritions.size(), raw_name);

        NutritionProduct nutrition = calculateMedianHandler(nutritions, normalize(raw_name));

        if (!repo.addEntry(nutrition, "openfood_median")){
            // TODO logger: source (service), label (error), message(failed to store in db).
            log.error("Failed to store median entry for {}", raw_name);
        }
        writeToLog(nutritions, raw_name, nutrition);
        return Optional.of(nutrition); 
    }

    private NutritionResponse convertToResponse(NutritionProduct p, Set<Field> fs, double grams) {

        Map<Field, Double> result = new HashMap<>();

        if (grams <= 0) {
           grams = 100; // Default to 100
           // throw new IllegalArgumentException("grams must be > 0");
        }

        double factor = grams / 100.0;

        for (Field f : fs) {
            Double value = f.get(p);
            if (value != null){
                result.put(f, (double) Math.round(value * factor));
            }
        }

        return new NutritionResponse(p.getName(), result);
    }


    // Get the list of fetched nutrients, compute the median, create a new product, return the new product
    private NutritionProduct calculateMedianHandler(List<NutritionProduct> nutritions, String name){

        Map<Field, List<Double>> values_per_field = new EnumMap<>(Field.class);

        NutritionProduct median = new NutritionProduct();
        
        for(Field f: Field.values()){
            values_per_field.put(f, new ArrayList<>());
        }
        
        for(NutritionProduct p : nutritions){
            for (Field f: Field.values()){                
                Double value = f.get(p);
                values_per_field.get(f).add(value);
            }

            
        }
        

        for(Field f : Field.values()){
            List<Double> values = values_per_field.get(f);
            Double medianValue = calculateMedian(values);
            System.out.print("\nMEDIAN: " + medianValue + " for: " +  f + "\n");


            switch (f) {
            case CALORIES -> median.setCalories(medianValue);
            case FAT -> median.setFat(medianValue);
            case SATURATED_FAT -> median.setSatFat(medianValue);
            case TRANS_FAT -> median.setTransFat(medianValue);
            case CHOLESTEROL -> median.setCholesterol(medianValue);
            case CARBOHYDRATES -> median.setCarbs(medianValue);
            case SUGARS -> median.setSugars(medianValue);
            case ADDED_SUGARS -> median.setAddedSugars(medianValue);
            case SUCROSE -> median.setSucrose(medianValue);
            case GLUCOSE -> median.setGlucose(medianValue);
            case FRUCTOSE -> median.setFructose(medianValue);
            case LACTOSE -> median.setLactose(medianValue);
            case STARCH -> median.setStarch(medianValue);
            case FIBER -> median.setFiber(medianValue);
            case PROTEINS -> median.setProtein(medianValue);
            case SALT -> median.setSalt(medianValue);
            case ADDED_SALT -> median.setAddedSalt(medianValue);
            case SODIUM -> median.setSodium(medianValue);
            case VITAMIN_C -> median.setVitaminC(medianValue);
            case VITAMIN_B1 -> median.setVitaminB1(medianValue);
            case VITAMIN_B2 -> median.setVitaminB2(medianValue);
            case VITAMIN_PP -> median.setVitaminPP(medianValue);
            case VITAMIN_B6 -> median.setVitaminB6(medianValue);
            case VITAMIN_B9 -> median.setVitaminB9(medianValue);
            case VITAMIN_B12 -> median.setVitaminB12(medianValue);
            case POTASSIUM -> median.setPotassium(medianValue);
            case CALCIUM -> median.setCalcium(medianValue);
            case IRON -> median.setIron(medianValue);
            case MAGNESIUM -> median.setMagnesium(medianValue);
            case ZINC -> median.setZinc(medianValue);
        }
    }
    median.setName(name);

    log.info("Median calculated + "+ median);
    return median;
}

    // Unsorted
    private Double calculateMedian(List<Double> data){

        

        Double median;

        if (data.size() == 1){
            return data.get(0);
        }
        
        // Dynamically allocated list for unknown amounts of null values
        List<Double> no_nulls_data = new ArrayList<>();
        
        // Discard nulls!
        for(int i = 0; i < data.size(); i ++){
            if (data.get(i) != null){
                no_nulls_data.add(data.get(i));
            } 
        }
        
        if(no_nulls_data.size() < 1){
            return null;
        }
        
        // If null is first item, it throws an exception because it cant sort nulls...
        Collections.sort(no_nulls_data);

        
        median = no_nulls_data.get(no_nulls_data.size()/2);

        return median;

    }


/*     public NutritionResponse calculate(NutritionResponse nutrition, double amount, Unit unit) {

        // Validate resource
        
        // fetch resource (like oats)

        // return nutritional value

        double grams = convertToGrams(amount, unit);

        double factor = grams / 100.0;

        // TODO: Handle this factor here? Or just return the fetched Nutrition Response, alternatively decide how to convert between units
        return new NutritionResponse(
        );
    }
    } */


        // UTILS

    private String normalize(String name){
        // Add more eventually
        return name.toLowerCase().trim();
    }

    private void writeToLog(List <NutritionProduct> discards, String name, NutritionProduct median){
        
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        String log_name = name.replace(" ", "_") + "_"+ currentDate +"_"+ currentTime.format(DateTimeFormatter.ofPattern("HHmmss")) + ".csv";

        Path logFile = Paths.get("data", "logs", log_name);

        try (BufferedWriter writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8, CREATE, APPEND)) {
            writer.write("product_name");
            for(Field f: Field.values()){
                String fieldData = f.getDbColumn();
                writer.write(',');
                writer.write(fieldData);
            }
            for (NutritionProduct p : discards){
                String data = p.returnAsCommaSeparatedString();
                System.out.print("Writing " + p.getName() + " " + p.getId() + " to log.\n" );
                writer.write(data);
            }    
            writer.newLine();
            writer.write("### COMPUTED MEDIAN ###");
            writer.newLine();
            writer.write(median.returnAsCommaSeparatedString());

        } catch (IOException x) {
            System.err.println(x);
            return;
        }
        
        System.out.printf("Logfile saved as: %s\n",log_name);
        return;

    }
}