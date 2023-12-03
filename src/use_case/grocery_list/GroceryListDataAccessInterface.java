package use_case.grocery_list;

import entity.GroceryList;

import java.util.UUID;

public interface GroceryListDataAccessInterface {
    boolean existsById(UUID id);
    void save(GroceryList groceryList);
}
