package data_access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import entity.GroceryList;
import entity.GroceryListFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class GrocerySerializerTest {
    String testJsonPath = "./groceryList.json";
    File jsonFile;

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
            jsonFile = new File(testJsonPath);
        } catch (IOException e){
            throw new RuntimeException();
        }

    }

    /**
     * Tests that new entry saved in grocery list file/
     */
    @Test
    public void testSerializer() throws IOException {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("5 pcs bananas");
        ingredients.add("2 tbps sugar");
        GroceryList groceryList = groceryListFactory.create(testGroceryListUUID, "Sample", UUID.randomUUID(), LocalDateTime.now(), ingredients);
        fileGroceryListDataAccessObject.save(groceryList);

        initializeGroceryListMap(fileGroceryListDataAccessObject);

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(GroceryList.class, new GroceryListSerializer());
        objectMapper.registerModule(module);

        try {
            objectMapper.writeValue(jsonFile, groceryListMap.values());
        } catch (IOException e){
            throw new RuntimeException();
        }

        assert fileGroceryListDataAccessObject.existsById(testGroceryListUUID);
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
