package use_case.grocery_list;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

public class GroceryListOutputData {
    private LocalDateTime dateCreated;
    private Map<String, String> ingredients;

    GroceryListOutputData(LocalDateTime dateCreated, Map<String, String> ingredients){
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    public LocalDateTime getDateCreated() { return this.dateCreated; }
    public Map<String, String> getIngredients() { return this.ingredients; }


}
