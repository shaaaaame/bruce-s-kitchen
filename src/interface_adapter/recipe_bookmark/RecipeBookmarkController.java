package interface_adapter.recipe_bookmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import use_case.recipe_bookmark.RecipeBookmarkInputBoundary;
import use_case.recipe_bookmark.RecipeBookmarkInputData;
import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInputData;

import java.util.UUID;

public class RecipeBookmarkController {
    final RecipeBookmarkInputBoundary recipeBookmarkInteractor;

    public RecipeBookmarkController(RecipeBookmarkInputBoundary recipeBookmarkInteractor) {
        this.recipeBookmarkInteractor = recipeBookmarkInteractor;
    }

    public void execute(UUID recipeId, String username) {
        RecipeBookmarkInputData recipeBookmarkInputData = new RecipeBookmarkInputData(recipeId, username);
        recipeBookmarkInteractor.execute(recipeBookmarkInputData);
    }
}
