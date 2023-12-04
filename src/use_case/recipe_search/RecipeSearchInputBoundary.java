package use_case.recipe_search;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RecipeSearchInputBoundary {
    public void execute(RecipeSearchInputData recipeSearchInputData) throws JsonProcessingException;
}