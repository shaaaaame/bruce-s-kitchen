package interface_adapter.recipe_bookmark;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class GetBookmarkedState {
    private List<Recipe> recipeList = new ArrayList<Recipe>();
    public GetBookmarkedState(){}

    public List<Recipe> getRecipeList(){ return this.recipeList; }

    public void setRecipeList(List<Recipe> recipeList) { this.recipeList = recipeList; }
}
