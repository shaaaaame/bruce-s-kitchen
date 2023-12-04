package use_case.recipe_create;
import java.util.List;
import java.time.LocalDateTime;

public class CreateRecipeOutputDta {

    private String instructions;
    private List<String> ingredients;
    private LocalDateTime dateCreated;
    private String name;

    CreateRecipeOutputDta(String name, LocalDateTime dateCreated) {
        this.name = name;
        this.dateCreated = dateCreated;
    }
    public LocalDateTime getDateCreated() { return this.dateCreated; }
    public String getName() { return this.name; }

}
