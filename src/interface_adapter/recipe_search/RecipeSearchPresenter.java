package interface_adapter.recipe_search;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

import java.util.List;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(RecipeSearchViewModel recipeSearchViewModel, ViewManagerModel viewManagerModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData recipeOutputData) {
        List<Recipe> recipeList = recipeOutputData.getRecipeList();

        RecipeSearchState currentState = recipeSearchViewModel.getState();
        currentState.setRecipeList(recipeList);

        recipeSearchViewModel.setState(currentState);
        recipeSearchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
