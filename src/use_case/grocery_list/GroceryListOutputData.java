package use_case.grocery_list;

import java.util.Date;
import java.util.Map;

public class GroceryListOutputData {
    private Date date_created;
    private Map<String, String> ingredients;

    GroceryListOutputData(Date date_created, Map<String, String> ingredients){
        this.date_created = date_created;
        this.ingredients = ingredients;
    }

    public Date getDateCreated() { return this.date_created; }
    public Map<String, String> getIngredients() { return this.ingredients; }


}
