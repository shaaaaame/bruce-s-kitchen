package use_case.get_bookmarked;

import entity.Recipe;

import java.util.List;

public class GetBookmarkedOutputData {
    private List<Recipe> recipeList;

    GetBookmarkedOutputData(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }

    public List<Recipe> getRecipeList() {
        return this.recipeList;
    }

}
