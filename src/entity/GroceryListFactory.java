package entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class GroceryListFactory {
    public GroceryList create(UUID groceryId, String name, UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        return new GroceryList(groceryId, name, userId, dateCreated, ingredients);
    }
    public GroceryList create(String name, UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        return new GroceryList(name, userId, dateCreated, ingredients);
    }
}
