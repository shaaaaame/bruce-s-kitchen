package use_case.grocery_list;

import data_access.FileGroceryListDataAccessObject;
import entity.GroceryList;
import entity.GroceryListFactory;

import java.time.LocalDateTime;

public class GroceryListInteractor implements GroceryListInputBoundary{

    final GroceryListDataAccessInterface groceryListDataAccessObject;
    final GroceryListFactory groceryListFactory;
    final GroceryListOutputBoundary groceryPresenter;
    public GroceryListInteractor(GroceryListDataAccessInterface groceryListDataAccessObject,
                                 GroceryListFactory groceryListFactory,
                                 GroceryListOutputBoundary groceryListOutputBoundary) {
        this.groceryListDataAccessObject = groceryListDataAccessObject;
        this.groceryListFactory = groceryListFactory;
        this.groceryPresenter = groceryListOutputBoundary;
    }

    @Override
    public void execute(GroceryListInputData groceryListInputData) {
        if (!groceryListDataAccessObject.existsById(groceryListInputData.getGroceryId())){
            groceryPresenter.prepareFailView("groceryId is not unique!");
        } else {
            GroceryList groceryList = groceryListFactory.create(
                    groceryListInputData.getUserId(),
                    LocalDateTime.now(),
                    groceryListInputData.getIngredients()
            );
            groceryListDataAccessObject.save(groceryList);

            GroceryListOutputData groceryListOutputData = new GroceryListOutputData(
                    groceryList.getDate(),
                    groceryList.getIngredients()
            );
            groceryPresenter.prepareSuccessView(groceryListOutputData);
        }
    }
}
