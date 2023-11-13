package entity;

import java.time.LocalDateTime;
import java.util.Map;

public class GroceryListFactory {
    public GroceryList create(int user_id, LocalDateTime date_created, Map<String, String> ingredients){
        return new GroceryList(user_id, date_created, ingredients);
    }
}
