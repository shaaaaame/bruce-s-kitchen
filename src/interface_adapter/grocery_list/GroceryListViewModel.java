package interface_adapter.grocery_list;

import interface_adapter.ViewModel;

public class GroceryListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "make a new grocery list";

    private GroceryListState state = new GroceryListState();

    public void setState(GroceryListState state) { this.state = state; }
    public GroceryListState getState() {return state; }

    public GroceryListViewModel() {
        super("Grocery List");
    }
}
