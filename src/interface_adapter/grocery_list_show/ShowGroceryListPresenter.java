package interface_adapter.grocery_list_show;

import interface_adapter.ViewManagerModel;
import use_case.grocery_list_show.ShowGroceryListOutputBoundary;
import use_case.grocery_list_show.ShowGroceryListOutputData;

public class ShowGroceryListPresenter implements ShowGroceryListOutputBoundary {

    private final ShowGroceryListViewModel showGroceryListViewModel;
    private ViewManagerModel viewManagerModel;

    public ShowGroceryListPresenter(ViewManagerModel viewManagerModel,
                          ShowGroceryListViewModel showGroceryListViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.showGroceryListViewModel = showGroceryListViewModel;
    }

    @Override
    public void prepareSuccessView(ShowGroceryListOutputData showGroceryListOutputData) {
        ShowGroceryListState showGroceryListState = showGroceryListViewModel.getState();
        showGroceryListState.setGroceryLists(showGroceryListOutputData.getGroceryLists());
        this.showGroceryListViewModel.setState(showGroceryListState);
        this.showGroceryListViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        //TODO: show empty
    }
}
