package interface_adapter.show_grocery_list;

import entity.GroceryList;
import interface_adapter.grocery_list.GroceryListState;
import interface_adapter.grocery_list.GroceryListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShowGroceryListState {
    private List<GroceryList> groceryLists = new ArrayList<GroceryList>();
    public ShowGroceryListState(){
    }

    public List<GroceryList> getGroceryLists(){ return this.groceryLists; }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }
}
