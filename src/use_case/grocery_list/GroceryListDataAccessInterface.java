package use_case.grocery_list;

import entity.GroceryList;

import java.util.List;
import java.util.UUID;

public interface GroceryListDataAccessInterface {
    boolean existsById(UUID id);
    void save(GroceryList groceryList);
    public GroceryList getByGroceryId(UUID id);
    public List<GroceryList> getByUserId(UUID userId);
    public List<GroceryList> getAll();
}
