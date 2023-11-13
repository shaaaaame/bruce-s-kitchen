package use_case.grocery_list;

import java.util.Date;
import java.util.Map;

public class GroceryListInputData {
    private final int grocery_id;
    private final int user_id;
    private final Date date_created;
    private Map<String, String> ingredients; // ingredient name : quantity;

    GroceryListInputData(int grocery_id, int user_id, Date date_created, Map<String, String> ingredients){
        this.grocery_id = grocery_id;
        this.user_id = user_id;
        this.date_created = date_created;
        this.ingredients = ingredients;
    }

    int getGroceryId(){ return this.grocery_id; }
    int getUserId(){ return this.user_id; }
    Date getDate(){ return this.date_created; }
    Map<String, String> getIngredients() { return this.ingredients; }
}
