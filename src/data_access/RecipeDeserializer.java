package data_access;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import entity.Recipe;
import entity.Tag;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class RecipeDeserializer extends StdDeserializer<List<Recipe>> {
    public RecipeDeserializer(){
        this(null);
    }
    public RecipeDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public List<Recipe> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        List<Recipe> recipeLists = new ArrayList<Recipe>();
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);


        if(root.isArray()){
            for(JsonNode node: root){

                UUID recipeId = null;
                UUID userId = null;

                JsonNode recipeIdNode = node.get("recipeId");
                if (recipeIdNode != null) {
                    recipeId = UUID.fromString(recipeIdNode.asText());
                }

                JsonNode userIdNode = node.get("userId");
                if (userIdNode != null && !userIdNode.isNull()) {
                    userId = UUID.fromString(userIdNode.asText());
                }


                String name = node.get("title").asText();
                String servings = node.get("servings").asText();

                LocalDateTime dateCreated = LocalDateTime.now();
                JsonNode timeIdNode = node.get("recipeId");
                if (timeIdNode != null) {
                    dateCreated = LocalDateTime.parse(node.get("dateCreated").asText());
                }

                Tag[] tags;
                JsonNode tagNode = node.get("tags");
                if (tagNode != null) {
                    tags = new Tag[tagNode.size()];
                    int i = 0;
                    for(JsonNode branch: tagNode) {
                        tags[i] = (new Tag(branch.asText()));
                        i++;
                    }
                }
                else {
                    tags = new Tag[0]; //change
                }


                String instructions = node.get("instructions").asText();

                List<String> ingredients = new ArrayList<>();
                JsonNode ingredientNode = node.get("ingredients");

                if (ingredientNode.isArray()){
                    for (JsonNode ingredientBranch: ingredientNode){
                        ingredients.add(ingredientBranch.asText());
                    }
                } else{
                    ingredients = new ArrayList<String>(List.of(ingredientNode.asText().split("\\|")));
                    System.out.println(ingredients);
                }

                recipeLists.add(new Recipe(recipeId, userId, name, servings, ingredients, tags, instructions, dateCreated));
            }

        }

        return recipeLists;
    }
}