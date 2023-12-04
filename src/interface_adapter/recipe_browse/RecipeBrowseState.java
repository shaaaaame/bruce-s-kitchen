package interface_adapter.recipe_browse;

import entity.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RecipeBrowseState {
    private Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();
    public RecipeBrowseState(){}

    public Map<UUID, Recipe> getRecipeMap(){return this.recipeMap;}
    public void setRecipeMap(Map<UUID, Recipe> recipeMap){
        this.recipeMap = recipeMap;
    }
}
