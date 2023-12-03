package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class GroceryList {

    private final UUID groceryId;
    private final String name;

    private final UUID userId;
    private final LocalDateTime dateCreated;
    private List<String> ingredients; // ingredient name : quantity;

    public GroceryList(UUID groceryId, String name, UUID userId, LocalDateTime dateCreated, List<String> ingredients){
        this.groceryId = groceryId;
        this.name = name;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    public GroceryList(String name, UUID userId, LocalDateTime dateCreated, List<String> ingredients){
        this.groceryId = UUID.randomUUID();
        this.name = name;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
    }

    public UUID getGroceryId(){ return this.groceryId; }
    public UUID getUserId(){ return this.userId; }
    public LocalDateTime getDate(){ return this.dateCreated; }
    public List<String> getIngredients() { return this.ingredients; }
    public String getName() { return this.name; }


}

