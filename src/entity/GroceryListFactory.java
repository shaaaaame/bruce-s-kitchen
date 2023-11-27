package entity;

import java.time.LocalDateTime;
import java.util.Map;

public class GroceryListFactory {
    public GroceryList create(int userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        return new GroceryList(userId, dateCreated, ingredients);
    }
}
