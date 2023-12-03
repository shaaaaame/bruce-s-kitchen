package use_case.grocery_list;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GroceryListOutputData {
    private LocalDateTime dateCreated;
    private List<String> ingredients;

    GroceryListOutputData(LocalDateTime dateCreated, List<String> ingredients){
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    public LocalDateTime getDateCreated() { return this.dateCreated; }
    public List<String> getIngredients() { return this.ingredients; }


}
