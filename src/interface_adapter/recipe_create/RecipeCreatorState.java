package interface_adapter.recipe_create;

import entity.Tag;
import java.util.UUID;
import java.util.List;

import java.util.ArrayList;

public class RecipeCreatorState {

    private String name = "";
    private Tag[] tags;
    private String instructions = "";
    private String servings = "0";
    private UUID user_id;
    private List<String> ingredients = new ArrayList<>();
    private UUID recipe_Id;

    public RecipeCreatorState(){
    }

    public String getName(){ return this.name; }
    public UUID getUser_id(){ return this.user_id; }
    public List<String> getIngredients(){ return this.ingredients; }
    public String getServings() { return this.servings; }
    public Tag[] getTags() { return this.tags; }
    public String getInstructions(){ return this.instructions; }

    public void setName(String name) { this.name = name; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
    public void setUser_id(UUID userId) { this.user_id = userId; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public void setTags(Tag[] tags) { this.tags = tags; }
    public void setServings(String servings ) { this.servings = servings; }



}
