package entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class GroceryListFactory {
    public GroceryList create(UUID groceryList, UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        return new GroceryList(groceryList, userId, dateCreated, ingredients);
    }
    public GroceryList create(UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        return new GroceryList(userId, dateCreated, ingredients);
    }
}
