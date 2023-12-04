package use_case.recipe_search;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_access.InMemoryRecipeAPIDataAccessObject;
import entity.Recipe;
import entity.RecipeFactory;

import java.util.List;

public class RecipeSearchInteractor implements RecipeSearchInputBoundary{

    final RecipeFactory recipeSearchFactory;
    final RecipeSearchOutputBoundary recipePresenter;
    final InMemoryRecipeAPIDataAccessObject recipeSearchObject;
    public RecipeSearchInteractor(InMemoryRecipeAPIDataAccessObject recipeSearchObject,
                                  RecipeFactory recipeSearchFactory,
                                  RecipeSearchOutputBoundary recipePresenter) {
        this.recipeSearchFactory = recipeSearchFactory;
        this.recipePresenter = recipePresenter;
        this.recipeSearchObject = recipeSearchObject;
    }

    @Override
    public void execute(RecipeSearchInputData recipeInputData) throws JsonProcessingException {

        List<Recipe> recipeList = this.recipeSearchObject.searchRecipe(recipeInputData.getSearch());
        RecipeSearchOutputData recipeOutputData;
        if (recipeList.isEmpty()) {
            recipeOutputData = new RecipeSearchOutputData(recipeList, true);
        } else {
            recipeOutputData = new RecipeSearchOutputData(recipeList, false);
        }
        recipePresenter.prepareSuccessView(recipeOutputData);
    }
}