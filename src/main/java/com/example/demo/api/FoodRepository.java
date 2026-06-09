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
                    p.setCalories(rs.getObject("energy_kcal_100g",Double.class));
                    p.setFat(rs.getObject("fat_100g",Double.class));
                    p.setSatFat(rs.getObject("saturated_fat_100g",Double.class));
                    p.setTransFat(rs.getObject("trans_fat_100g",Double.class));
                    p.setCholesterol(rs.getObject("cholesterol_100g",Double.class));
                    p.setCarbs(rs.getObject("carbohydrates_100g",Double.class));
                    p.setSugars(rs.getObject("sugars_100g",Double.class));
                    p.setAddedSugars(rs.getObject("added_sugars_100g",Double.class));
                    p.setSucrose(rs.getObject("sucrose_100g",Double.class));
                    p.setGlucose(rs.getObject("glucose_100g",Double.class));
                    p.setFructose(rs.getObject("fructose_100g",Double.class));
                    p.setLactose(rs.getObject("lactose_100g",Double.class));
                    p.setStarch(rs.getObject("starch_100g",Double.class));
                    p.setFiber(rs.getObject("fiber_100g",Double.class));
                    p.setProtein(rs.getObject("proteins_100g", Double.class));
                    p.setSalt(rs.getObject("salt_100g",Double.class));
                    p.setAddedSalt(rs.getObject("added_salt_100g",Double.class));
                    p.setSodium(rs.getObject("sodium_100g",Double.class));
                    p.setVitaminC(rs.getObject("vitamin_c_100g",Double.class));
                    p.setVitaminB1(rs.getObject("vitamin_b1_100g",Double.class));
                    p.setVitaminB2(rs.getObject("vitamin_b2_100g",Double.class));
                    p.setVitaminPP(rs.getObject("vitamin_pp_100g",Double.class));
                    p.setVitaminB6(rs.getObject("vitamin_b6_100g",Double.class));
                    p.setVitaminB9(rs.getObject("vitamin_b9_100g",Double.class));
                    p.setVitaminB12(rs.getObject("vitamin_b12_100g",Double.class));
                    p.setPotassium(rs.getObject("potassium_100g",Double.class));
                    p.setCalcium(rs.getObject("calcium_100g",Double.class));
                    p.setIron(rs.getObject("iron_100g",Double.class));
                    p.setMagnesium(rs.getObject("magnesium_100g",Double.class));
                    p.setZinc(rs.getObject("zinc_100g",Double.class));
    
                    return p;
                },
                product_name
                );
                System.out.print("[REPO] Fetched ok\n");
                return products;
    }
}
