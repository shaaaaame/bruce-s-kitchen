package use_case.grocery_list;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class GroceryListInputData {
    private final UUID userId;
    private String name;
    private Map<String, String> ingredients; // ingredient name : quantity;

    public GroceryListInputData(String name, UUID userId, Map<String, String> ingredients){
        this.name = name;
        this.userId = userId;
        this.ingredients = ingredients;
    }

    UUID getUserId(){ return this.userId; }
    Map<String, String> getIngredients() { return this.ingredients; }

    public String getName() { return this.name; }
}
