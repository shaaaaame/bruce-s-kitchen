package data_access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import entity.Recipe;
import entity.RecipeFactory;
import use_case.APIpull.RecipeDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import java.io.IOException;


public class RecipeFileDataAccessObject implements RecipeDataAccessInterface {

    private final Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();
    private final String JSON_PATH = "./recipesList.json";
    private final File jsonFile;
    private RecipeFactory recipeFactory;

    public RecipeFileDataAccessObject() throws IOException {
        this.recipeFactory = new RecipeFactory();
        jsonFile = new File(JSON_PATH);
        jsonFile.createNewFile();

        if (jsonFile.length() == 0){
            this.save();
        } else {
            initializeRecipeMap();
        }
    }

    @Override
    public boolean existsByID(UUID identifier) {
        return recipeMap.containsKey(identifier);
    }

    @Override
    public void save(Recipe recipe) {
        recipeMap.put(recipe.getRecipe_id(), recipe);
        this.save();
    }

    private void save(){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Recipe.class, new RecipeSerializer());
        objectMapper.registerModule(module);

        try {
            objectMapper.writeValue(jsonFile, recipeMap.values());
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    public Recipe getByRecipeId(UUID id){
        return recipeMap.get(id);
    }

    public Recipe[] getByUserId(UUID userId) {
        List<Recipe> RecipeLists = new ArrayList<Recipe>();
        for (Recipe recipe : recipeMap.values()) {
            if (recipe.getUserId() == userId) {
                RecipeLists.add(recipe);
            }
        }
        return RecipeLists.toArray(new Recipe[]{});
    }

    public void deleteRecipe(UUID id){
        recipeMap.remove(id);
        this.save();
    }

    /*
    initializing recipe map from json file
     */
    private void initializeRecipeMap() throws IOException{
        StringBuilder jsonStringBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            String row;
            while ((row = reader.readLine()) != null) {
                jsonStringBuilder.append(row);
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new RecipeDeserializer());
        objectMapper.registerModule(module);

        List<Recipe> recipes = objectMapper.readValue(jsonStringBuilder.toString(), List.class);
        for (Recipe  recipe: recipes){
            recipeMap.put(recipe.getRecipe_id(), recipe);
        }
    }
}