package use_case.grocery_list_show;

import view.ShowGroceryListView;

import java.util.List;
import java.util.UUID;

public class ShowGroceryListInputData {
    private UUID userId = null;

    public ShowGroceryListInputData(UUID userId){
        this.userId = userId;
    }

    public ShowGroceryListInputData(){
    }

    UUID getUserId(){ return this.userId; }
}
