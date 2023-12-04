package interface_adapter.show_grocery_list;

import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInputData;
import use_case.grocery_list.ShowGroceryListInputBoundary;
import use_case.grocery_list.ShowGroceryListInputData;

import java.util.List;
import java.util.UUID;

public class ShowGroceryListController {
    final ShowGroceryListInputBoundary showGroceryListInteractor;

    public ShowGroceryListController(ShowGroceryListInputBoundary showGroceryListInputBoundary){
        this.showGroceryListInteractor = showGroceryListInputBoundary;
    }

    public void execute(UUID userId){
        ShowGroceryListInputData showGroceryListInputData = new ShowGroceryListInputData(userId);

        showGroceryListInteractor.execute(showGroceryListInputData);
    }
}
