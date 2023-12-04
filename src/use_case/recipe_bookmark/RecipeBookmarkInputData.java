package use_case.recipe_bookmark;

import java.util.UUID;

public class RecipeBookmarkInputData {
    private UUID recipeId;
    private String username;
    public RecipeBookmarkInputData(UUID recipeId, String username) {
        this.recipeId = recipeId;
        this.username = username;
    }
    public UUID getRecipeId(){return this.recipeId;}
    public String getUsername(){return this.username;}
}
