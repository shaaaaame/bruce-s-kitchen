package view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import data_access.FileGroceryListDataAccessObject;
import data_access.GroceryListsDeserializer;
import entity.GroceryList;
import entity.GroceryListFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.grocery_list.GroceryListInputData;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.*;

public class GroceryListTest {

    String testJsonPath = "./groceryList.json";
    Map<UUID, GroceryList> groceryListMap = new HashMap<UUID, GroceryList>();
    UUID testGroceryListUUID = UUID.randomUUID();
    GroceryListFactory groceryListFactory = new GroceryListFactory();

    FileGroceryListDataAccessObject fileGroceryListDataAccessObject;

    /**
     * ensures that there exists at least 1 GroceryList when testing
     */
    @Before
    public void init(){
        try{
            fileGroceryListDataAccessObject = new FileGroceryListDataAccessObject();
            initializeGroceryListMap(fileGroceryListDataAccessObject);
        } catch (IOException e){
            throw new RuntimeException();
        }

    }

    /**
     * Tests that new entry saved in grocery list file/
     */
    @Test
    public void testGroceryListSavedToFile(){
        try{
            int entries = groceryListMap.size();
            createGroceryList(fileGroceryListDataAccessObject);
            initializeGroceryListMap(fileGroceryListDataAccessObject);
            int new_entries = groceryListMap.size();

            assert(new_entries == entries + 1);

        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Test
    public void testDeleteGroceryList(){
        try{
            createGroceryList(fileGroceryListDataAccessObject);
            initializeGroceryListMap(fileGroceryListDataAccessObject);
            int entries = groceryListMap.size();
            fileGroceryListDataAccessObject.deleteGroceryList(testGroceryListUUID);
            initializeGroceryListMap(fileGroceryListDataAccessObject);
            int new_entries = groceryListMap.size();
            assert(new_entries == entries - 1);

        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    private void initializeGroceryListMap(FileGroceryListDataAccessObject fileGroceryListDataAccessObject) throws IOException{
        groceryListMap.clear();
        StringBuilder jsonStringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(testJsonPath))) {
            String row;
            while ((row = reader.readLine()) != null) {
                jsonStringBuilder.append(row);
            }
        }

        if (jsonStringBuilder.toString().equals("") || jsonStringBuilder.toString().equals("[]")){
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new GroceryListsDeserializer());
        objectMapper.registerModule(module);

        List<GroceryList> groceryLists = objectMapper.readValue(jsonStringBuilder.toString(), List.class);
        for (GroceryList gl : groceryLists){
            groceryListMap.put(gl.getGroceryId(), gl);
        }
    }

    public void createGroceryList(FileGroceryListDataAccessObject fileGroceryListDataAccessObject){

        List<String> ingredients = new ArrayList<>();
        ingredients.add("5 pcs bananas");
        ingredients.add("2 tbps sugar");

        GroceryList groceryList = groceryListFactory.create(testGroceryListUUID, "Name", UUID.randomUUID(), LocalDateTime.now(), ingredients);
        fileGroceryListDataAccessObject.save(groceryList);
    }
}
