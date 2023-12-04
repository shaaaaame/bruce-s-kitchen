package interface_adapter.recipe_bookmark;

import use_case.get_bookmarked.GetBookmarkedInputBoundary;
import use_case.get_bookmarked.GetBookmarkedInputData;
import use_case.recipe_bookmark.RecipeBookmarkInputBoundary;
import use_case.recipe_bookmark.RecipeBookmarkInputData;

import java.util.UUID;

public class GetBookmarkedController {
    final GetBookmarkedInputBoundary getBookmarkedInputBoundary;

    public GetBookmarkedController(GetBookmarkedInputBoundary getBookmarkedInputBoundary) {
        this.getBookmarkedInputBoundary = getBookmarkedInputBoundary;
    }

    public void execute(String username) {
        GetBookmarkedInputData getBookmarkedInputData = new GetBookmarkedInputData(username);
        getBookmarkedInputBoundary.execute(getBookmarkedInputData);
    }
}
