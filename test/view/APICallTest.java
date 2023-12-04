import data_access.FileGroceryListDataAccessObject;
import data_access.InMemoryRecipeAPIDataAccessObject;
import entity.GroceryList;
import entity.GroceryListFactory;
import entity.Recipe;
import entity.RecipeFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class APICallTest {

    Map<UUID, GroceryList> recipeMap = new HashMap<UUID, GroceryList>();
    RecipeFactory recipeFactory = new RecipeFactory();
    InMemoryRecipeAPIDataAccessObject inMemoryRecipeAPIDataAccessObject;

    @Before
    public void init(){
        try{
            InMemoryRecipeAPIDataAccessObject inMemoryRecipeAPIDataAccessObject = new InMemoryRecipeAPIDataAccessObject();
        } catch (IOException e){
            throw new RuntimeException();
        }

    }
    @Test
    public void testAPICall(){
        try{
            InMemoryRecipeAPIDataAccessObject inMemoryRecipeAPIDataAccessObject = new InMemoryRecipeAPIDataAccessObject();
            List<Recipe> recipes = inMemoryRecipeAPIDataAccessObject.searchRecipe("onion");
            for (Recipe r : recipes){
                assert (r.name instanceof java.lang.String);
            }
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Test
    public void testNullUser(){
        try{
            InMemoryRecipeAPIDataAccessObject inMemoryRecipeAPIDataAccessObject = new InMemoryRecipeAPIDataAccessObject();
            List<Recipe> recipes = inMemoryRecipeAPIDataAccessObject.searchRecipe("onion");
            for (Recipe r : recipes){
                assert (r.getUserId() == null);
            }
        }catch (IOException e){
            throw new RuntimeException();
        }
    }
}
