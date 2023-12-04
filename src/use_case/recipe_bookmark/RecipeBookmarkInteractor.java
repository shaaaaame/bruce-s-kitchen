package use_case.recipe_bookmark;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryRecipeAPIDataAccessObject;
import entity.Recipe;
import entity.RecipeFactory;
import entity.User;
import use_case.recipe_search.RecipeSearchInputData;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

import java.util.List;

public class RecipeBookmarkInteractor implements RecipeBookmarkInputBoundary {
    final FileUserDataAccessObject userDataAccessObject;
    final InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject;
    public RecipeBookmarkInteractor(FileUserDataAccessObject userDataAccessObject,
                                  InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.recipeAPIDataAccessObject = recipeAPIDataAccessObject;
    }

    @Override
    public void execute(RecipeBookmarkInputData recipeBookmarkInputData) {

        User user = userDataAccessObject.get(recipeBookmarkInputData.getUsername());

        user.addBookmarkedRecipe(recipeBookmarkInputData.getRecipeId());
        userDataAccessObject.save();

    }
}
