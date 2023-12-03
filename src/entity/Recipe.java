package entity;

import java.util.HashMap;

public class Recipe {
    private UUID recipe_id;
    private UUID user_id; // foreign key, who the recipe belongs to
    public String name;
    public String servings;
    public List<String> ingredients;
    public Tag[] tags;
    public String instructions;
    private LocalDateTime dateCreated;

    public Recipe(UUID recipe_id, UUID user_id, String name, String servings, List<String> ingredients, Tag[] tags, String instructions, LocalDateTime dateCreated){
        this.recipe_id = recipe_id;
        this.user_id = user_id;
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.tags = tags;
        this.instructions = instructions;
        this.dateCreated = dateCreated;
    }

    public Recipe(UUID user_id, String name, String servings, List<String> ingredients, Tag[] tags, String instructions, LocalDateTime dateCreated){
        this.recipe_id = UUID.randomUUID();
        this.user_id = user_id;
        this.name = name;
        this.servings = servings;
        this.ingredients = ingredients;
        this.tags = tags;
        this.instructions = instructions;
        this.dateCreated = dateCreated;
    }

    public UUID getRecipe_id(){return this.recipe_id; }
    public UUID getUserId(){ return this.user_id; }
    public LocalDateTime getDate(){ return this.dateCreated; }
    public List<String> getIngredients() { return this.ingredients; }
    public String getName() { return this.name; }
    public String getInstructions(){ return this.instructions; }

}