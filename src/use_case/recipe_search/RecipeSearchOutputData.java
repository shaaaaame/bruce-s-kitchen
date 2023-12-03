package use_case.recipe_search;

import entity.Recipe;

import java.util.List;

public class RecipeSearchOutputData {

    private List<Recipe> recipeList;
    private boolean useCaseFailed;

    RecipeSearchOutputData(List<Recipe> recipeList, boolean useCaseFailed){
        this.recipeList = recipeList;
        this.useCaseFailed = useCaseFailed;
    }

}
