package use_case.createRecipe;
import java.util.List;
import java.time.LocalDateTime;

public class CreateRecipeOutputDta {

    private String instructions;
    private List<String> ingredients;
    private LocalDateTime dateCreated;
    private String name;

    CreateRecipeOutputDta(String instructions, List<String> ingredients, String name, LocalDateTime dateCreated) {
        this.ingredients = ingredients;
        this.name = name;
        this.instructions = instructions;
        this.dateCreated = dateCreated;
    }

}
