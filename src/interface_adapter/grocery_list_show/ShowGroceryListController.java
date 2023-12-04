package interface_adapter.grocery_list_show;

import use_case.grocery_list_show.ShowGroceryListInputBoundary;
import use_case.grocery_list_show.ShowGroceryListInputData;

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
