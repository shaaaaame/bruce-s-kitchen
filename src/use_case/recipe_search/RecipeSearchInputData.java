package use_case.recipe_search;

import java.util.UUID;

public class RecipeSearchInputData {
    private String search;
    public RecipeSearchInputData(String search) {
        this.search = search;
    }
    public String getSearch(){return this.search;}
}