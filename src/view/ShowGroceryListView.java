package view;

import entity.GroceryList;
import interface_adapter.grocery_list.GroceryListState;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.show_grocery_list.ShowGroceryListController;
import interface_adapter.show_grocery_list.ShowGroceryListState;
import interface_adapter.show_grocery_list.ShowGroceryListViewModel;
import view.grocery_list.SingleGroceryList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowGroceryListView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Grocery List";
    private final ShowGroceryListViewModel showGroceryListViewModel;
    private final ShowGroceryListController showGroceryListController;

    private List<GroceryList> groceryLists = new ArrayList<>();
    private List<String> groceryNames = new ArrayList<>();
    private Map<String, String> nameToIdString = new HashMap<String, String>();

    public ShowGroceryListView(ShowGroceryListViewModel showGroceryListViewModel, ShowGroceryListController showGroceryListController) {
        this.showGroceryListViewModel = showGroceryListViewModel;
        this.showGroceryListController = showGroceryListController;
        showGroceryListViewModel.addPropertyChangeListener(this);

        this.showGroceryListController.execute(null);
        ShowGroceryListState showGroceryListState = this.showGroceryListViewModel.getState();

        groceryLists = showGroceryListState.getGroceryLists();

        for (GroceryList grocery : groceryLists){
            groceryNames.add(grocery.getName());
        }

        JLabel title = new JLabel(ShowGroceryListViewModel.TITLE_LABEL);
        JPanel titleSpace = new JPanel();

        JList<String> groceryNamesJList = new JList<>(groceryNames.toArray(new String[0]));
        JScrollPane groceryListScrollPane = new JScrollPane(groceryNamesJList);
        JPanel mainContent = new JPanel();
        CardLayout singleItemCard = new CardLayout();
        JLabel emptyText = new JLabel("Make a selection or make a new grocery list :D");
        JPanel emptyCard = new JPanel();
        JPanel singleItem = new JPanel(singleItemCard);
        for (Integer i = 0; i < groceryLists.size(); i++){
            singleItem.add(new SingleGroceryList(groceryLists.get(i)), String.valueOf(i));
        }

        JPanel select = new JPanel();
        select.add(new JLabel("Select from list!"));

        JPanel buttons = new JPanel();
        JButton back = new JButton("Back");

        emptyCard.setLayout(new BorderLayout());
        emptyCard.add(emptyText, BorderLayout.CENTER);
        emptyCard.setBackground(new Color(255, 255, 255));
        emptyCard.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        titleSpace.setMaximumSize(new Dimension(9999, 100));
        titleSpace.setLayout(new BorderLayout());
        titleSpace.add(title, BorderLayout.LINE_START);
        titleSpace.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        groceryListScrollPane.setMinimumSize(new Dimension(300, 600));
        singleItem.setMinimumSize(new Dimension(300, 600));
        groceryListScrollPane.setBorder(BorderFactory.createLineBorder(Colors.dark));
        singleItem.setBorder(BorderFactory.createLineBorder(Colors.dark));
        groceryNamesJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        groceryNamesJList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getSource().equals(groceryNamesJList)){
                            singleItemCard.show(singleItem, String.valueOf(e.getLastIndex()));
                        }
                    }
                }
        );


        mainContent.setLayout(new GridLayout(1, 2, 15, 15));
        mainContent.setMinimumSize(new Dimension(700, 300));
        mainContent.add(groceryListScrollPane);
        mainContent.add(singleItem);

        singleItem.add(emptyCard, "Empty");
        singleItemCard.show(singleItem, "Empty");

        buttons.add(back, FlowLayout.LEFT);

        this.add(titleSpace);
        this.add(mainContent);
        this.add(buttons);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
