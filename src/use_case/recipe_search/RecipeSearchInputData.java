package use_case.recipe_search;

import java.util.UUID;

public class RecipeSearchInputData {
    private final UUID userid;
    private String search;
    public RecipeSearchInputData(UUID userid, String search) {
        this.userid = userid;
        this.search = search;
    }

    UUID getUserid(){return this.userid;}
    public String getSearch(){return this.search;}
}
