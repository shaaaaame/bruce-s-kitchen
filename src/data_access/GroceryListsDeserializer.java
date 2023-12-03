package data_access;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;
import entity.GroceryList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class GroceryListsDeserializer extends StdDeserializer<List<GroceryList>> {
    public GroceryListsDeserializer(){
        this(null);
    }
    public GroceryListsDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<GroceryList> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        List<GroceryList> groceryLists = new ArrayList<GroceryList>();
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        if(root.isArray()){
            for(JsonNode node: root){
                UUID groceryId = UUID.fromString(node.get("groceryId").asText());
                String name = node.get("name").asText();
                UUID userId = node.get("userId").isNull() ? null : UUID.fromString(node.get("userId").asText());
                LocalDateTime dateCreated = LocalDateTime.parse(node.get("dateCreated").asText());
                List<String> ingredients = new ArrayList<String>();

                JsonNode ingredientNode = node.get("ingredients");
                Iterator<String> ingredientNames = ingredientNode.fieldNames();

                while (ingredientNames.hasNext()){
                    String fieldName = ingredientNames.next();
                    ingredients.add(ingredientNode.get(fieldName).toString());
                }

                groceryLists.add(new GroceryList(groceryId, name, userId, dateCreated, ingredients));
            }

        }

        return groceryLists;
    }
}
