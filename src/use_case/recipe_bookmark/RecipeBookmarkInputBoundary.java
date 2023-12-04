package use_case.recipe_bookmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import use_case.recipe_search.RecipeSearchInputData;

public interface RecipeBookmarkInputBoundary {
    public void execute(RecipeBookmarkInputData recipeBookmarkInputData);

}
