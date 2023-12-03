package app;

import data_access.FileGroceryListDataAccessObject;
import entity.GroceryListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list.GroceryListController;
import interface_adapter.grocery_list.GroceryListPresenter;
import interface_adapter.grocery_list.GroceryListViewModel;
import use_case.grocery_list.GroceryListInputBoundary;
import use_case.grocery_list.GroceryListInputData;
import use_case.grocery_list.GroceryListInteractor;
import use_case.grocery_list.GroceryListOutputBoundary;
import view.GroceryListView;

import javax.swing.*;
import java.io.IOException;

public class GroceryListUseCaseFactory {

    public static GroceryListView create(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel, FileGroceryListDataAccessObject groceryListDataAccessObject){
        try {
            GroceryListController groceryListController = createGroceryListUseCase(viewManagerModel, groceryListViewModel, groceryListDataAccessObject);
            return new GroceryListView(groceryListController, groceryListViewModel);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Cannot open grocery list data file");
        }

        return null;
    }

    private static GroceryListController createGroceryListUseCase(ViewManagerModel viewManagerModel, GroceryListViewModel groceryListViewModel, FileGroceryListDataAccessObject groceryListDataAccessObject) throws IOException {
        GroceryListOutputBoundary groceryListOutputBoundary = new GroceryListPresenter(viewManagerModel, groceryListViewModel);
        GroceryListFactory groceryListFactory = new GroceryListFactory();
        GroceryListInputBoundary groceryListInteractor = new GroceryListInteractor(groceryListDataAccessObject, groceryListFactory, groceryListOutputBoundary);

        return new GroceryListController(groceryListInteractor);
    }
}
