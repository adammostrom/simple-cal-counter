package com.example.demo.service;

import static java.nio.file.StandardOpenOption.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.example.demo.api.NutritionApi;
import com.example.demo.api.NutritionRepository;
import com.example.demo.models.Field;
import com.example.demo.models.NutritionProduct;
import com.example.demo.models.NutritionResponse;
import com.example.demo.models.Unit;

@Service
public class NutritionService {


    //private NutritionCache cache;

    private NutritionRepository repo;
    private NutritionApi api;
    private NutritionCache cache;

    public NutritionService(NutritionRepository repo, NutritionCache cache, NutritionApi api) {
        this.repo = repo;
        this.cache = cache;
        this.api = api;
    }



    private static final Map<Field, Function<NutritionProduct, Double>> FIELD_MAP =
        Map.ofEntries(
            Map.entry(Field.CALORIES, NutritionProduct::getEnergy_kcal),
            Map.entry(Field.FAT, NutritionProduct::getFat),
            Map.entry(Field.SATURATED_FAT, NutritionProduct::getSaturated_fat),
            Map.entry(Field.TRANS_FAT, NutritionProduct::getTrans_fat),
            Map.entry(Field.CHOLESTEROL, NutritionProduct::getCholesterol),
            Map.entry(Field.CARBOHYDRATES, NutritionProduct::getCarbohydrates),
            Map.entry(Field.SUGARS, NutritionProduct::getSugars),
            Map.entry(Field.ADDED_SUGARS, NutritionProduct::getAdded_sugars),
            Map.entry(Field.SUCROSE, NutritionProduct::getSucrose),
            Map.entry(Field.GLUCOSE, NutritionProduct::getGlucose),
            Map.entry(Field.FRUCTOSE, NutritionProduct::getFructose),
            Map.entry(Field.LACTOSE, NutritionProduct::getLactose),
            Map.entry(Field.STARCH, NutritionProduct::getStarch),
            Map.entry(Field.FIBER, NutritionProduct::getFiber),
            Map.entry(Field.PROTEINS, NutritionProduct::getProteins),
            Map.entry(Field.SALT, NutritionProduct::getSalt),
            Map.entry(Field.ADDED_SALT, NutritionProduct::getAdded_salt),
            Map.entry(Field.SODIUM, NutritionProduct::getSodium),
            Map.entry(Field.VITAMIN_C, NutritionProduct::getVitamin_c),
            Map.entry(Field.VITAMIN_B1, NutritionProduct::getVitamin_b1),
            Map.entry(Field.VITAMIN_B2, NutritionProduct::getVitamin_b2),
            Map.entry(Field.VITAMIN_PP, NutritionProduct::getVitamin_pp),
            Map.entry(Field.VITAMIN_B6, NutritionProduct::getVitamin_b6),
            Map.entry(Field.VITAMIN_B9, NutritionProduct::getVitamin_b9),
            Map.entry(Field.VITAMIN_B12, NutritionProduct::getVitamin_b12),
            Map.entry(Field.POTASSIUM, NutritionProduct::getPotassium),
            Map.entry(Field.CALCIUM, NutritionProduct::getCalcium),
            Map.entry(Field.IRON, NutritionProduct::getIron),
            Map.entry(Field.MAGNESIUM, NutritionProduct::getMagnesium),
            Map.entry(Field.ZINC, NutritionProduct::getZinc)

        );


    // The primary "get" function that controller calls on.
    // Fetches result, converts to NutritionResponse, returns
    // TODO: Maybe add nutritionrequest here instead (POST request) with fields to get
    public NutritionResponse get(String raw, double amount, Unit unit, Set<Field> fields) {

        String name = normalize(raw);
        try {
            
            NutritionProduct nutrition = cache.fetch(normalize(name));
            if (nutrition == null) {
                // Check DB
                nutrition = fetchDB(normalize(raw), fields);
                if (nutrition == null){
                    // Check api, then ->
                    throw new IllegalArgumentException("Item not found: " + raw);
                } else {
                    // Set the name to lower case so we will match in the cache
                    nutrition.setName(nutrition.getName());
                    cache.store(nutrition);
                }
            }
            
        // Compute amount and unit
        return convertToResponse(nutrition, fields);

        } catch (Exception e) {
            throw e;
        }
    }

    private List <NutritionProduct> eliminateMissingRequestFields(List<NutritionProduct> nutritions, Set<Field> fields){
        Iterator<NutritionProduct> it = nutritions.iterator();

        while (it.hasNext()) {
            NutritionProduct p = it.next();

            boolean valid = true;

            for (Field f : fields) {
                    Function<NutritionProduct, Double> extractor = FIELD_MAP.get(f);

                if (extractor == null || extractor.apply(p) == null) {
                    valid = false;
                    break;
                }
            }

            if (!valid) {
                System.out.println("Removed: " + p.getName());
                it.remove();
            }
        }
        return nutritions;
    }

    public NutritionProduct fetchDB(String raw_name, Set<Field> fields) {

        // First, check if median exists:
        // TODO: Refactor, its a bit ugly and unstable
        List <NutritionProduct> nutritions = new ArrayList<NutritionProduct>();
        
        nutritions = repo.find(raw_name, "openfood_median");
        if (!nutritions.isEmpty()){
        
            System.out.printf("\nFound median-computed entry: %d\n", nutritions.size());
            // The median table SHOULD only return one value
            return nutritions.get(0);
        }
        nutritions.clear();
        // fetchAndStore should only apply for full fetching
        nutritions = repo.find(raw_name, "openfood_raw");

        System.out.printf("Found %d rows\n", nutritions.size());

        // Compute the median approach
        NutritionProduct nutrition = calculateMedianHandler(nutritions, normalize(raw_name));
        if (!repo.addEntry(nutrition, "openfood_median")){
            System.out.print("failed to store in db\n");
        }
        return nutrition; 
    }

    private NutritionResponse convertToResponse(NutritionProduct p, Set<Field> fs){

        Map<Field, Double> result = new HashMap<>();

        for(Field f: fs){
            Function<NutritionProduct, Double> extractor = FIELD_MAP.get(f);
            if (extractor != null){
                result.put(f, extractor.apply(p));
            }
        }

        return new NutritionResponse(p.getName(), result);
    }

    /*
    TODO:

            // Check API.
        nutrition = api.fetch(name);
        if (nutrition != null) {
            cache.store(nutrition);
            repo.addEntry(nutrition);        
            return nutrition;
        }
    
    */


    private String normalize(String name){
        // Add more eventually
        return name.toLowerCase().trim();
    }

    private void writeToLog(List <NutritionProduct> discards, String name){
        
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        
        String log_name = name.replace(" ", "_") +"_"+ currentDate +"_"+ currentTime + ".csv";

        Path logFile = Paths.get("data", "logs", log_name);
        
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(logFile, CREATE, APPEND))) {

            for (NutritionProduct p : discards){
                byte data[] = p.returnAsCommaSeparatedString().getBytes();
                System.out.print("Writing " + p.getName() + " " + p.getId() + " to log.\n" );
                out.write(data, 0, data.length);
            }    

        } catch (IOException x) {
            System.err.println(x);
        }
        
        System.out.printf("Logfile saved as: %s\n",log_name);
        return;

    }


    // Get the list of fetched nutrients, compute the median, create a new product, return the new product
    private NutritionProduct calculateMedianHandler(List<NutritionProduct> nutritions, String name){

        
        // Creates a Field (CALORIES) key value to a list of values
        Map<Field, List<Double>> values_per_field = new EnumMap<>(Field.class);

        for(Field f: Field.values()){
            values_per_field.put(f, new ArrayList<>());
        }

        for(NutritionProduct p : nutritions){
            for (Field f: Field.values()){

                Function<NutritionProduct, Double> getter = FIELD_MAP.get(f);
                Double value = getter.apply(p);
                values_per_field.get(f).add(value);
            }
        }


        NutritionProduct median = new NutritionProduct();

        for(Field f : Field.values()){
            List<Double> values = values_per_field.get(f);
            Double medianValue = calculateMedian(values);

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
    writeToLog(nutritions, name);
    return median;
}

    // Unsorted
    private Double calculateMedian(List<Double> data){

        Double median;

        if (data.size() == 1){
            return data.get(0);
        }
        // Sort the array
        //Arrays.sort(data);

        Collections.sort(data);

        // Dynamically allocated list for unknown amounts of null values
        List<Double> no_nulls_data = new ArrayList<>();
        
        // Discard nulls!
        for(int i = 0; i < data.size(); i ++){
            if (data.get(i) != null){
                no_nulls_data.add(data.get(i));
            } 
        }

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

    private double convertToGrams(double amount, Unit unit) {
        switch (unit) {
            case GRAM: return amount;
            case DL: return amount * 100;   // rough assumption, based on density, viscosity etc. Add functions for this
            case CUPS: return amount * 240; // rough
            default: throw new IllegalArgumentException("Unknown unit");
        }
    } */

}