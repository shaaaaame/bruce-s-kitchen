package interface_adapter.recipeCreator;

import java.util.ArrayList;

public class RecipeCreatorState {

    private String name = "";
    private int duration = 0;
    private ArrayList<String> tags = new ArrayList<>();
    private String instructions = "";
    private int servings = 0;

    public RecipeCreatorState(RecipeCreatorState copy){
        name = copy.name;
        duration = copy.duration;
        tags = copy.tags;
        instructions =copy.instructions;
        servings = copy.servings;
    }
    public RecipeCreatorState(){
    }

}
