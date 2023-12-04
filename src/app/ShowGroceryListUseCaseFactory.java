package app;

import data_access.FileGroceryListDataAccessObject;
import entity.GroceryListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list.GroceryListController;
import interface_adapter.grocery_list.GroceryListPresenter;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.show_grocery_list.ShowGroceryListController;
import interface_adapter.show_grocery_list.ShowGroceryListPresenter;
import interface_adapter.show_grocery_list.ShowGroceryListViewModel;
import use_case.grocery_list.*;
import view.GroceryListView;
import view.ShowGroceryListView;

import javax.swing.*;
import java.io.IOException;

public class ShowGroceryListUseCaseFactory {
    public static ShowGroceryListView create(ViewManagerModel viewManagerModel, ShowGroceryListViewModel showGroceryListViewModel, FileGroceryListDataAccessObject groceryListDataAccessObject){
        try {
            ShowGroceryListController showGroceryListController = createShowGroceryListUseCase(viewManagerModel, showGroceryListViewModel, groceryListDataAccessObject);
            return new ShowGroceryListView(showGroceryListViewModel, showGroceryListController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Cannot open grocery list data file");
        }

        return null;
    }

    private static ShowGroceryListController createShowGroceryListUseCase(ViewManagerModel viewManagerModel, ShowGroceryListViewModel showGroceryListViewModel, FileGroceryListDataAccessObject groceryListDataAccessObject) throws IOException {
        ShowGroceryListOutputBoundary showGroceryListOutputBoundary = new ShowGroceryListPresenter(viewManagerModel, showGroceryListViewModel);
        GroceryListFactory groceryListFactory = new GroceryListFactory();
        ShowGroceryListInputBoundary showGroceryListInteractor = new ShowGroceryListInteractor(groceryListDataAccessObject, groceryListFactory, showGroceryListOutputBoundary);

        return new ShowGroceryListController(showGroceryListInteractor);
    }
}
