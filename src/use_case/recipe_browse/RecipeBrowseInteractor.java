package use_case.recipe_browse;

import com.fasterxml.jackson.core.JsonProcessingException;
import data_access.RecipeFileDataAccessObject;
import entity.Recipe;
import use_case.recipe_search.RecipeSearchOutputBoundary;

import java.util.Map;
import java.util.UUID;

public class RecipeBrowseInteractor implements RecipeBrowseInputBoundary{

    final RecipeFileDataAccessObject recipeFileDataAccessObject;
    final RecipeBrowseOutputBoundary recipeBrowsePresenter;

    public RecipeBrowseInteractor(RecipeFileDataAccessObject recipeFileDataAccessObject,
                                  RecipeBrowseOutputBoundary recipeBrowsePresenter) {
        this.recipeFileDataAccessObject = recipeFileDataAccessObject;
        this.recipeBrowsePresenter = recipeBrowsePresenter;
    }

    @Override
    public void execute(){
        Map<UUID, Recipe> recipeMap = this.recipeFileDataAccessObject.getRecipeMap();
        RecipeBrowseOutputData recipeBrowseOutputData;
        if (recipeMap.isEmpty()){
            recipeBrowseOutputData = new RecipeBrowseOutputData(recipeMap, true);
        } else {
            recipeBrowseOutputData = new RecipeBrowseOutputData(recipeMap, false);
        }
        recipeBrowsePresenter.prepareSuccessView(recipeBrowseOutputData);
    }
}
