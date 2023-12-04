package interface_adapter.grocery_list_show;

import entity.GroceryList;

import java.util.ArrayList;
import java.util.List;

public class ShowGroceryListState {
    private List<GroceryList> groceryLists = new ArrayList<GroceryList>();
    public ShowGroceryListState(){
    }

    public List<GroceryList> getGroceryLists(){ return this.groceryLists; }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }
}
