package use_case.grocery_list;

public interface ShowGroceryListOutputBoundary {
    public void prepareSuccessView(ShowGroceryListOutputData showGroceryListOutputData);
    public void prepareFailView(String error);
}