package entity;

import java.util.HashMap;

public class Recipe {
    public int user_id; // foreign key, who the recipe belongs to
    public String name;
    public int servings;
    public HashMap<String, String> ingredients;
    public int duration; // in minutes
    public String[] tags;
    public String instructions;
    Recipe(
            int user_id,
            String name,
            int servings,
            HashMap<String, String> ingredients,
            int duration,
            String[] tags,
            String instructions
    ){

    }



}