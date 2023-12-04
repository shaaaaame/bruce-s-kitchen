package interface_adapter.recipeCreator;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeCreatorViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Recipe creator";
    public static final String DONE_LABEL = "Done";
    private RecipeCreatorState state = new RecipeCreatorState();
    public void setState(RecipeCreatorState state) { this.state = state; }
    public RecipeCreatorViewModel() {
        super("recipeCreator");
    }
    public RecipeCreatorState getState() {
        return state;
    }
}
