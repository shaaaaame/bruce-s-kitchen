package use_case.grocery_list_show;

import entity.GroceryList;
import entity.GroceryListFactory;
import use_case.grocery_list.GroceryListDataAccessInterface;

import java.util.List;
import java.util.UUID;

public class ShowGroceryListInteractor implements ShowGroceryListInputBoundary {
    final GroceryListDataAccessInterface groceryListDataAccessObject;
    final GroceryListFactory groceryListFactory;
    final ShowGroceryListOutputBoundary showGroceryPresenter;
    public ShowGroceryListInteractor(GroceryListDataAccessInterface groceryListDataAccessObject,
                                 GroceryListFactory groceryListFactory,
                                 ShowGroceryListOutputBoundary showGroceryListOutputBoundary) {
        this.groceryListDataAccessObject = groceryListDataAccessObject;
        this.groceryListFactory = groceryListFactory;
        this.showGroceryPresenter = showGroceryListOutputBoundary;
    }

    @Override
    public void execute(ShowGroceryListInputData showGroceryListInputData) {

        UUID userId = showGroceryListInputData.getUserId();
        List<GroceryList> groceryLists;
        if(userId != null){
            groceryLists = this.groceryListDataAccessObject.getByUserId(userId);
        } else {
            groceryLists = this.groceryListDataAccessObject.getAll();
        }

        if (groceryLists.size() == 0){
            showGroceryPresenter.prepareFailView("No grocery lists yet :(");
        } else {
            ShowGroceryListOutputData showGroceryListOutputData = new ShowGroceryListOutputData(groceryLists);
            showGroceryPresenter.prepareSuccessView(showGroceryListOutputData);
        }

    }
}
