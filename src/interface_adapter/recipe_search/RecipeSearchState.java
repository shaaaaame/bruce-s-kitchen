package interface_adapter.recipe_search;

public class RecipeSearchState {
    private String search = "";
    public RecipeSearchState(){}

    public String getSearch(){return this.search;}

    public void setSearch(String search){
        this.search = search;
    }

}
