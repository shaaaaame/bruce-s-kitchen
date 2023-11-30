package interface_adapter.grocery_list;

import interface_adapter.ViewManagerModel;
import use_case.grocery_list.GroceryListOutputBoundary;
import use_case.grocery_list.GroceryListOutputData;
import view.GroceryListView;

public class GroceryListPresenter implements GroceryListOutputBoundary {

    private final GroceryListViewModel groceryListViewModel;
    private ViewManagerModel viewManagerModel;

    public GroceryListPresenter(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel){
        this.groceryListViewModel = groceryListViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(GroceryListOutputData groceryListOutputData) {
        // TODO: redirect to home page
    }

    @Override
    public void prepareFailView(String error) {
        // TODO: create popup
    }
}
