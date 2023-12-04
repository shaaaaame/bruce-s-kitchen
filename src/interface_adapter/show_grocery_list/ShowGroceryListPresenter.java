package interface_adapter.show_grocery_list;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.grocery_list.ShowGroceryListInputBoundary;
import use_case.grocery_list.ShowGroceryListInputData;
import use_case.grocery_list.ShowGroceryListOutputBoundary;
import use_case.grocery_list.ShowGroceryListOutputData;

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
