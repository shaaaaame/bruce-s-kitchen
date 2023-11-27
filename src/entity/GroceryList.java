package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class GroceryList {

    private final UUID groceryId;
    private final UUID userId;
    private final LocalDateTime dateCreated;
    private Map<String, String> ingredients; // ingredient name : quantity;

    public GroceryList(UUID groceryId, UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        this.groceryId = groceryId;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    public GroceryList(UUID userId, LocalDateTime dateCreated, Map<String, String> ingredients){
        this.groceryId = UUID.randomUUID();
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    public UUID getGroceryId(){ return this.groceryId; }
    public UUID getUserId(){ return this.userId; }
    public LocalDateTime getDate(){ return this.dateCreated; }
    public Map<String, String> getIngredients() { return this.ingredients; }


}

