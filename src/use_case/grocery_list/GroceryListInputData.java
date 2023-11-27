package use_case.grocery_list;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class GroceryListInputData {
    private final UUID groceryId;
    private final UUID userId;
    private final LocalDateTime dateCreated;
    private Map<String, String> ingredients; // ingredient name : quantity;

    GroceryListInputData(UUID groceryId, UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        this.groceryId = groceryId;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    UUID getGroceryId(){ return this.groceryId; }
    UUID getUserId(){ return this.userId; }
    LocalDateTime getDate(){ return this.dateCreated; }
    Map<String, String> getIngredients() { return this.ingredients; }
}
