package data_access;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import entity.GroceryList;
import entity.GroceryListFactory;
import org.json.JSONArray;
import use_case.grocery_list.GroceryListDataAccessInterface;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class FileGroceryListDataAccessObject implements GroceryListDataAccessInterface {
    private final String JSON_PATH = "./groceryList.json";
    private final Map<UUID, GroceryList> groceryListMap = new HashMap<UUID, GroceryList>();
    private final File jsonFile;
    private GroceryListFactory groceryListFactory;

    public FileGroceryListDataAccessObject() throws IOException {
        this.groceryListFactory = new GroceryListFactory();
        jsonFile = new File(JSON_PATH);
        jsonFile.createNewFile();

        if (jsonFile.length() == 0){
            this.save();
        } else {
            initializeGroceryListMap();
        }
    }

    @Override
    public boolean existsById(UUID id) {
        return groceryListMap.containsKey(id);
    }

    @Override
    public void save(GroceryList groceryList) {
        groceryListMap.put(groceryList.getGroceryId(), groceryList);
        this.save();
    }

    private void save(){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(GroceryList.class, new GroceryListSerializer());
        objectMapper.registerModule(module);

        try {
            objectMapper.writeValue(jsonFile, groceryListMap.values());
        } catch (IOException e){
            throw new RuntimeException();
        }

    }

    public GroceryList getByGroceryId(UUID id){
        return groceryListMap.get(id);
    }
    public GroceryList[] getByUserId(UUID userId) {
        List<GroceryList> groceryLists = new ArrayList<GroceryList>();
        for(GroceryList groceryList : groceryListMap.values()){
            if (groceryList.getUserId() == userId){
                groceryLists.add(groceryList);
            }
        }

        return groceryLists.toArray(new GroceryList[]{});
    }

    public void deleteGroceryList(UUID id){
        groceryListMap.remove(id);
        this.save();
    }

    /**
     * initializes grocery list map from the json file using ObjectMapper.
     */
    private void initializeGroceryListMap() throws IOException{
        StringBuilder jsonStringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String row;
            while ((row = reader.readLine()) != null) {
                jsonStringBuilder.append(row);
            }
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

}
