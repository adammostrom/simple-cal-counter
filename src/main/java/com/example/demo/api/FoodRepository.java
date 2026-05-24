package com.example.demo.api;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.CalorieDTO;
import com.example.demo.models.NutritionProduct;
import com.example.demo.models.NutritionResponse;


@Repository
public class FoodRepository implements NutritionRepository{
    


    
    private final JdbcTemplate jdbc;


    
    public FoodRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }



    public boolean addEntry(NutritionResponse entry){
        return false;// TODO
    }

public List<CalorieDTO> findCalories_DTO(String prod_name) {

    String query = """
                SELECT product_name, energy_kcal_100g
                FROM openfood_raw
                WHERE product_name ILIKE ?
                LIMIT 50
                """;
    return jdbc.query(
        query,
        (rs, rowNum) -> new CalorieDTO(
            rs.getString("product_name"),
            rs.getDouble("energy_kcal_100g")
        ),
        "%" + prod_name + "%"
    );
}


public List<NutritionProduct> find(String product_name){

    System.out.println("REPO HIT");
    
    String query = """
        SELECT *
        FROM openfood_raw
        WHERE product_name ILIKE ?
        LIMIT 50
    """;
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
            "%" + product_name + "%"
            );
            System.out.print("Fetched ok\n");
            return products;
    }
}
