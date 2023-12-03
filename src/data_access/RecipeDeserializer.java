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
                UUID recipeId = UUID.fromString(node.get("recipeID").asText());
                UUID userId = UUID.fromString(node.get("userId").asText());
                String name = node.get("name").asText();
                String servings = node.get("servings").asText();
                LocalDateTime dateCreated = LocalDateTime.parse(node.get("dateCreated").asText());

                JsonNode tagNode = node.get("tags");
                Tag[] tags = new Tag[tagNode.size()];
                int i = 0;
                for(JsonNode branch: tagNode) {
                    tags[i] = (new Tag(branch.asText()));
                    i++;
                }

                String instructions = node.get("instructions").asText();

                List<String> ingredients = new ArrayList<>();
                JsonNode ingredientNode = node.get("ingredients");
                for (JsonNode ingredientBranch: ingredientNode){
                    ingredients.add(ingredientBranch.asText());
                }





                recipeLists.add(new Recipe(recipeId, userId, name, servings, ingredients, tags, instructions, dateCreated));
            }

        }

        return recipeLists;
    }
}
