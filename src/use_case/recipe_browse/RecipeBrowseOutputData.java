package use_case.recipe_browse;

import entity.Recipe;

import java.util.Map;
import java.util.UUID;

public class RecipeBrowseOutputData {
    private Map<UUID, Recipe> recipeMap;
    private boolean useCaseFailed;
    RecipeBrowseOutputData(Map<UUID, Recipe> recipeMap, boolean useCaseFailed){
        this.recipeMap = recipeMap;
        this.useCaseFailed = useCaseFailed;
    }

    public Map<UUID, Recipe> getRecipeMap() {return this.recipeMap;}
}
