package interface_adapter.grocery_list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroceryListState {

    private String name = "";
    private UUID groceryId;
    private UUID userId;
    private List<String> ingredients = new ArrayList<String>();
    public GroceryListState(){
    }

    public String getName(){ return this.name; }
    public UUID getGroceryId(){ return this.groceryId; }
    public UUID getUserId(){ return this.userId; }
    public List<String> getIngredients(){ return this.ingredients; }


    public void setName(String name) { this.name = name; }
    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setGroceryId(UUID groceryId) {
        this.groceryId = groceryId;
    }

    public void clear(){
        this.name = "";
        this.groceryId = null;
        this.userId = null;
        this.ingredients = new ArrayList<>();
    }
}
