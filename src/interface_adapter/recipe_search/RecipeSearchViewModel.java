package interface_adapter.recipe_search;

import interface_adapter.ViewModel;

public class RecipeSearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Search a recipe!";

    private RecipeSearchState state = new RecipeSearchState();

    public void setState(RecipeSearchState state) {this.state = state;}
    public RecipeSearchState getState() {return state;}

    public RecipeSearchViewModel(){
        super("Recipe Search");
    }
}
