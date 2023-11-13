package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class GroceryList {

    // used to auto-increment id when new list created
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int grocery_id;
    private final int user_id;
    private final LocalDateTime date_created;
    private Map<String, String> ingredients; // ingredient name : quantity;

    GroceryList(int user_id, LocalDateTime date_created, Map<String, String> ingredients){
        this.grocery_id = count.incrementAndGet();
        this.user_id = user_id;
        this.date_created = date_created;
        this.ingredients = ingredients;
    }

    public int getGroceryId(){ return this.grocery_id; }
    public int getUserId(){ return this.user_id; }
    public LocalDateTime getDate(){ return this.date_created; }
    public Map<String, String> getIngredients() { return this.ingredients; }


}

