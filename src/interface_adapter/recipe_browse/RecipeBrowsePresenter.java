package interface_adapter.recipe_browse;

import interface_adapter.ViewManagerModel;
import use_case.recipe_browse.RecipeBrowseOutputBoundary;
import use_case.recipe_browse.RecipeBrowseOutputData;


import javax.swing.text.View;

public class RecipeBrowsePresenter implements RecipeBrowseOutputBoundary {
    private final RecipeBrowseViewModel recipeBrowseViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeBrowsePresenter(RecipeBrowseViewModel recipeBrowseViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.recipeBrowseViewModel = recipeBrowseViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(RecipeBrowseOutputData recipeOutputData) {
        RecipeBrowseState recipeBrowseState = recipeBrowseViewModel.getState();
        recipeBrowseState.setRecipeMap(recipeOutputData.getRecipeMap());
        this.recipeBrowseViewModel.setState(recipeBrowseState);
        this.recipeBrowseViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
