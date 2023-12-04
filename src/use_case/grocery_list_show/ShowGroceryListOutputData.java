package use_case.grocery_list_show;

import entity.GroceryList;

import java.time.LocalDateTime;
import java.util.List;

public class ShowGroceryListOutputData {

    private List<GroceryList> groceryLists;

    ShowGroceryListOutputData(List<GroceryList> groceryLists){
        this.groceryLists = groceryLists;
    }

    public List<GroceryList> getGroceryLists() { return this.groceryLists; }


}
