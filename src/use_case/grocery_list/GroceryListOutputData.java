package use_case.grocery_list;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GroceryListOutputData {
    private LocalDateTime dateCreated;
    private List<String> ingredients;
    private String name;

    GroceryListOutputData(String name, LocalDateTime dateCreated, List<String> ingredients){
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
        this.name = name;
    }

    public LocalDateTime getDateCreated() { return this.dateCreated; }
    public List<String> getIngredients() { return this.ingredients; }

    public String getName() { return this.name; }


}
