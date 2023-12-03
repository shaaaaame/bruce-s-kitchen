package entity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Recipe {
    public UUID user_id; // foreign key, who the recipe belongs to
    public String name;
    public String servings;
    public HashMap<String, String> ingredients;
    public int duration; // in minutes
    public Tag[] tags;
    public String instructions;
    private LocalDateTime dateCreated;
    private UUID userId;

    public Recipe(UUID user_id, String name, String servings, HashMap<String, String> ingredients, Tag[] tags, String instructions, LocalDateTime dateCreated){
        this.user_id = user_id;
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.tags = tags;
        this.instructions = instructions;
        this.dateCreated = dateCreated;
    }

    public Recipe(UUID userId, String name, String servings, List<String> ingredients, List<Tag> tags, String instructions, LocalDateTime dateCreated) {
    }

    public UUID getUserId(){ return this.userId; }
    public LocalDateTime getDate(){ return this.dateCreated; }
    public Map<String, String> getIngredients() { return this.ingredients; }



}