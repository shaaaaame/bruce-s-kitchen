package interface_adapter.recipe_search;

import entity.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeSearchState {
    private String search = "";
    private List<Recipe> recipeList = new ArrayList<Recipe>();
    public RecipeSearchState(){}

    public String getSearch(){return this.search;}

    public List<Recipe> getRecipeList(){ return this.recipeList; }

    public void setSearch(String search){
        this.search = search;
    }
    public void setRecipeList(List<Recipe> recipeList) { this.recipeList = recipeList; }

}
