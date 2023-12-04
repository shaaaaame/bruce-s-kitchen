package interface_adapter.grocery_list_show;

import interface_adapter.ViewModel;

public class ShowGroceryListViewModel extends ViewModel {
    public static final String TITLE_LABEL = "show all grocery lists";
    private ShowGroceryListState state = new ShowGroceryListState();

    public void setState(ShowGroceryListState state) { this.state = state; }
    public ShowGroceryListState getState() {return state; }

    public ShowGroceryListViewModel() {
        super("Show Grocery List");
    }
}
