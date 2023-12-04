package interface_adapter.recipe_bookmark;

import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_browse.RecipeBrowseState;
import interface_adapter.recipe_browse.RecipeBrowseViewModel;
import use_case.get_bookmarked.GetBookmarkOutputBoundary;
import use_case.get_bookmarked.GetBookmarkedInputBoundary;
import use_case.get_bookmarked.GetBookmarkedOutputData;
import use_case.recipe_browse.RecipeBrowseOutputData;

public class GetBookmarkedPresenter implements GetBookmarkOutputBoundary {
    private final GetBookmarkedViewModel getBookmarkedViewModel;
    private ViewManagerModel viewManagerModel;

    public GetBookmarkedPresenter(GetBookmarkedViewModel getBookmarkedViewModel,
                                 ViewManagerModel viewManagerModel) {
        this.getBookmarkedViewModel = getBookmarkedViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GetBookmarkedOutputData getBookmarkedOutputData) {
        GetBookmarkedState getBookmarkedState = getBookmarkedViewModel.getState();
        getBookmarkedState.setRecipeList(getBookmarkedOutputData.getRecipeList());
        this.getBookmarkedViewModel.setState(getBookmarkedState);
        this.getBookmarkedViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}

