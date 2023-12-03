package interface_adapter.grocery_list;

import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInputData;
import use_case.grocery_list.GroceryListInteractor;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroceryListController {

    final GroceryListInputBoundary groceryListInteractor;

    public GroceryListController(GroceryListInputBoundary groceryListInputBoundary){
        this.groceryListInteractor = groceryListInputBoundary;
    }

    public void execute(String name, UUID userId, List<String> ingredients){
        GroceryListInputData groceryListInputData = new GroceryListInputData(
                name, userId, ingredients
        );

        groceryListInteractor.execute(groceryListInputData);
    }
}
