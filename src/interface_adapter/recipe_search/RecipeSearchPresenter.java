package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(RecipeSearchViewModel recipeSearchViewModel, ViewManagerModel viewManagerModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData recipeOutputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
