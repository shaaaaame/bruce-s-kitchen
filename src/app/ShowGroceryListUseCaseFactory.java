package app;

import data_access.FileGroceryListDataAccessObject;
import entity.GroceryListFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.grocery_list_show.ShowGroceryListController;
import interface_adapter.grocery_list_show.ShowGroceryListPresenter;
import interface_adapter.grocery_list_show.ShowGroceryListViewModel;
import use_case.grocery_list_show.ShowGroceryListInputBoundary;
import use_case.grocery_list_show.ShowGroceryListInteractor;
import use_case.grocery_list_show.ShowGroceryListOutputBoundary;
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
