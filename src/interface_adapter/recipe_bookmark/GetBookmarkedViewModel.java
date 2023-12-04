package interface_adapter.recipe_bookmark;

import interface_adapter.ViewModel;
import interface_adapter.recipe_search.RecipeSearchState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetBookmarkedViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Bookmarked Recipes!";

    private GetBookmarkedState state = new GetBookmarkedState();

    public void setState(GetBookmarkedState state) {this.state = state;}

    public GetBookmarkedViewModel(){
        super("Recipe Bookmark");
    }

    public GetBookmarkedState getState() {return state;}
}
