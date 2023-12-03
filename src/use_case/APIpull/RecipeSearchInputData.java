package use_case.APIpull;

import java.util.List;
import java.util.UUID;

public class RecipeSearchInputData {
    private final UUID userid;
    private String name;
    private List<String> ingredients;

    public RecipeSearchInputData(UUID userid, String name) {
        this.userid = userid;
        this.name = name;
        this.ingredients = ingredients;
    }
}
