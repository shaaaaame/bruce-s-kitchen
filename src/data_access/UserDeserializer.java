package data_access;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDeserializer extends StdDeserializer<User> {

    public UserDeserializer() {
        this(null);
    }

    public UserDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        String username = root.get("username").asText();
        String password = root.get("password").asText();
        List<UUID> groceryListIds = new ArrayList<>();
        List<String> bookmarkedRecipes = new ArrayList<>();

        JsonNode groceryListsNode = root.get("groceryLists");
        if (groceryListsNode.isArray()) {
            for (JsonNode groceryListNode : groceryListsNode) {
                groceryListIds.add(UUID.fromString(groceryListNode.asText()));
            }
        }

        JsonNode bookmarkedRecipesNode = root.get("bookmarkedRecipes");
        if (bookmarkedRecipesNode.isArray()) {
            for (JsonNode recipeNode : bookmarkedRecipesNode) {
                bookmarkedRecipes.add(recipeNode.asText());
            }
        }

        UUID id = UUID.fromString(root.get("id").asText());

        return new User(username, password, id);
    }
}