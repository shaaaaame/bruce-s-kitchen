package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final String username;

    private final String password;
    private final UUID user_id;

    private List<GroceryList> groceryLists;
    private List<UUID> bookmarkedRecipes;


    public User(String username, String password, UUID user_id) {
        this.username = username;
        this.password = password;
        this.user_id = user_id;
        this.groceryLists = new ArrayList<>();
        this.bookmarkedRecipes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UUID getID() { return user_id; }

    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    public List<UUID> getBookmarkedRecipes() {
        return bookmarkedRecipes;
    }
}


