package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GroceryListFactory {
    public GroceryList create(UUID groceryId, String name, UUID userId, LocalDateTime dateCreated, List<String> ingredients){
        return new GroceryList(groceryId, name, userId, dateCreated, ingredients);
    }
    public GroceryList create(String name, UUID userId, LocalDateTime dateCreated, List<String> ingredients){
        return new GroceryList(name, userId, dateCreated, ingredients);
    }
}
