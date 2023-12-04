package interface_adapter.recipe_create;
import interface_adapter.ViewModel;

public class RecipeCreatorViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Recipe creator";
    public static final String DONE_LABEL = "Done";
    private RecipeCreatorState state = new RecipeCreatorState();
    public void setState(RecipeCreatorState state) { this.state = state; }
    public RecipeCreatorViewModel() {
        super("Create Recipe");
    }
    public RecipeCreatorState getState() { return state; }
}
