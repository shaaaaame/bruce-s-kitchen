package use_case.grocery_list;

public interface GroceryListOutputBoundary {
    public void prepareSuccessView(GroceryListOutputData groceryListOutputData);
    public void prepareFailView(String error);
}
