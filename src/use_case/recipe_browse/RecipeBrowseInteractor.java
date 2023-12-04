package use_case.recipe_browse;

import data_access.InMemoryRecipeAPIDataAccessObject;
import data_access.RecipeFileDataAccessObject;
import entity.Recipe;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RecipeBrowseInteractor implements RecipeBrowseInputBoundary{

    final RecipeFileDataAccessObject recipeFileDataAccessObject;
    final InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject;
    final RecipeBrowseOutputBoundary recipeBrowsePresenter;

    public RecipeBrowseInteractor(RecipeFileDataAccessObject recipeFileDataAccessObject,
                                  InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject, RecipeBrowseOutputBoundary recipeBrowsePresenter) {
        this.recipeFileDataAccessObject = recipeFileDataAccessObject;
        this.recipeAPIDataAccessObject = recipeAPIDataAccessObject;
        this.recipeBrowsePresenter = recipeBrowsePresenter;
    }

    @Override
    public void execute(){
        Map<UUID, Recipe> recipeMapFile = this.recipeFileDataAccessObject.getRecipeMap();
        Map<UUID, Recipe> recipeMapAPI = this.recipeAPIDataAccessObject.getRecipeMap();
        Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();


        recipeMap.putAll(recipeMapFile);
        recipeMap.putAll(recipeMapAPI);

        RecipeBrowseOutputData recipeBrowseOutputData;
        if (recipeMap.isEmpty()){
            recipeBrowseOutputData = new RecipeBrowseOutputData(recipeMap, true);
        } else {
            recipeBrowseOutputData = new RecipeBrowseOutputData(recipeMap, false);
        }
        recipeBrowsePresenter.prepareSuccessView(recipeBrowseOutputData);
    }
}
