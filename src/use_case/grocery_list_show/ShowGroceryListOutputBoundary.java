package use_case.grocery_list_show;

public interface ShowGroceryListOutputBoundary {
    public void prepareSuccessView(ShowGroceryListOutputData showGroceryListOutputData);
    public void prepareFailView(String error);
}