package interface_adapter.recipe_search;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Recipe;
import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInputData;
import use_case.recipe_search.RecipeSearchInteractor;

public class RecipeSearchController {
    final RecipeSearchInputBoundary recipeSearchInteractor;

    public RecipeSearchController(RecipeSearchInputBoundary recipeSearchInputBoundary) {
        this.recipeSearchInteractor = recipeSearchInputBoundary;
    }

    public void execute(String search) throws JsonProcessingException {
        RecipeSearchInputData recipeSearchInputData = new RecipeSearchInputData(search);
        recipeSearchInteractor.execute(recipeSearchInputData);
    }
}
