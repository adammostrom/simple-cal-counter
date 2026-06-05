package com.example.demo.api;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.DBTables;
import com.example.demo.models.Field;
import com.example.demo.models.NutritionProduct;


@Repository
public class FoodRepository implements NutritionRepository{
    


    
    private final JdbcTemplate jdbc;


    
    public FoodRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean addEntry(NutritionProduct p, String table){
        
        StringBuilder fields = new StringBuilder("product_name");
        StringBuilder placeholders = new StringBuilder("?");

        for(Field f : Field.values()){
            fields.append(",").append(f.getDbColumn());
            placeholders.append(",?");
        }

        String query = "INSERT INTO " + table + " (" + fields + ") VALUES (" + placeholders + ")";

        int rows = jdbc.update(query,
                    p.getName(),
                    p.getEnergy_kcal(),
                    p.getFat(),
                    p.getSaturated_fat(),
                    p.getTrans_fat(),
                    p.getCholesterol(),
                    p.getCarbohydrates(),
                    p.getSugars(),
                    p.getAdded_sugars(),
                    p.getSucrose(),
                    p.getGlucose(),
                    p.getFructose(),
                    p.getLactose(),
                    p.getStarch(),
                    p.getFiber(),
                    p.getProteins(),
                    p.getSalt(),
                    p.getAdded_salt(),
                    p.getSodium(),
                    p.getVitamin_c(),
                    p.getVitamin_b1(),
                    p.getVitamin_b2(),
                    p.getVitamin_pp(),
                    p.getVitamin_b6(),
                    p.getVitamin_b9(),
                    p.getVitamin_b12(),
                    p.getPotassium(),
                    p.getCalcium(),
                    p.getIron(),
                    p.getMagnesium(),
                    p.getZinc()
        );
        // Insert succeeded
        return rows == 1;
    }

public List<NutritionProduct> findRaw(String product_name){
    return find(product_name, DBTables.OPENFOOD_RAW.getDbTable());
}

public List<NutritionProduct> findMedian(String product_name){
    return find(product_name, DBTables.OPENFOOD_MEDIAN.getDbTable());
}



public List<NutritionProduct> find(String product_name, String table){
        String query = " SELECT * FROM " + table + " WHERE LOWER(product_name) = LOWER(?)";
            List <NutritionProduct> products = jdbc.query(
                query,
                (rs, rowNum) -> {
                    NutritionProduct p = new NutritionProduct();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("product_name"));
                    p.setCalories(rs.getDouble("energy_kcal_100g"));
                    p.setFat(rs.getDouble("fat_100g"));
                    p.setSatFat(rs.getDouble("saturated_fat_100g"));
                    p.setTransFat(rs.getDouble("trans_fat_100g"));
                    p.setCholesterol(rs.getDouble("cholesterol_100g"));
                    p.setCarbs(rs.getDouble("carbohydrates_100g"));
                    p.setSugars(rs.getDouble("sugars_100g"));
                    p.setAddedSugars(rs.getDouble("added_sugars_100g"));
                    p.setSucrose(rs.getDouble("sucrose_100g"));
                    p.setGlucose(rs.getDouble("glucose_100g"));
                    p.setFructose(rs.getDouble("fructose_100g"));
                    p.setLactose(rs.getDouble("lactose_100g"));
                    p.setStarch(rs.getDouble("starch_100g"));
                    p.setFiber(rs.getDouble("fiber_100g"));
                    p.setProtein(rs.getDouble("proteins_100g"));
                    p.setSalt(rs.getDouble("salt_100g"));
                    p.setAddedSalt(rs.getDouble("added_salt_100g"));
                    p.setSodium(rs.getDouble("sodium_100g"));
                    p.setVitaminC(rs.getDouble("vitamin_c_100g"));
                    p.setVitaminB1(rs.getDouble("vitamin_b1_100g"));
                    p.setVitaminB2(rs.getDouble("vitamin_b2_100g"));
                    p.setVitaminPP(rs.getDouble("vitamin_pp_100g"));
                    p.setVitaminB6(rs.getDouble("vitamin_b6_100g"));
                    p.setVitaminB9(rs.getDouble("vitamin_b9_100g"));
                    p.setVitaminB12(rs.getDouble("vitamin_b12_100g"));
                    p.setPotassium(rs.getDouble("potassium_100g"));
                    p.setCalcium(rs.getDouble("calcium_100g"));
                    p.setIron(rs.getDouble("iron_100g"));
                    p.setMagnesium(rs.getDouble("magnesium_100g"));
                    p.setZinc(rs.getDouble("zinc_100g"));
    
                    return p;
                },
                product_name
                );
                System.out.print("[REPO] Fetched ok\n");
                return products;
    }
}
