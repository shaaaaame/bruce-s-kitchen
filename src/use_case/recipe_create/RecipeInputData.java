package use_case.recipe_create;

import java.util.List;
import java.util.UUID;

import entity.Tag;
public class RecipeInputData {
    private String name;
    private String servings;
    private List<String> ingredients;
    private String instructions;
    private UUID user_id;
    private Tag[] tags;

    public RecipeInputData(UUID user_id, String name, String servings, List<String> ingredients, Tag[] tags, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.servings = servings;
        this.user_id = user_id;
        this.tags = tags;
    }

    public String getName() { return this.name; }
    public List<String> getIngredients() { return this.ingredients; }
    public String getInstructions() { return this.instructions; }
    public String getServings() { return this.servings; }
    public UUID getUser_id() { return this.user_id; }
    public Tag[] getTags() { return this.tags; }

    }



