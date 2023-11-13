package use_case.grocery_list;

import entity.GroceryList;

public interface GroceryListDataAccessInterface {
    boolean existsById(int id);
    void save(GroceryList groceryList);
}
