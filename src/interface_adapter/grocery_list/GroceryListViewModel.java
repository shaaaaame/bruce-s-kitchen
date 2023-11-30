package interface_adapter.grocery_list;

import interface_adapter.ViewModel;

public class GroceryListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "make a new grocery list";

    public GroceryListViewModel(String viewName) {
        super("Grocery List");
    }
}
