package data_access;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entity.Recipe;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RecipeSerializer extends StdSerializer<Recipe>{

    public RecipeSerializer(){
        this(null);
    }
    public RecipeSerializer(Class<Recipe> t) {
        super(t);
    }

    @Override
    public void serialize(Recipe recipe, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("recipeID", recipe.getRecipe_id().toString());
        jsonGenerator.writeStringField("userId", recipe.getUserId() != null ? recipe.getUserId().toString() : null);
        jsonGenerator.writeStringField("name", recipe.name);
        jsonGenerator.writeStringField("servings", recipe.servings);
        jsonGenerator.writeStringField("dateCreated", recipe.getDate().toString());
        jsonGenerator.writeStringField("tags", Arrays.toString(recipe.tags));
        jsonGenerator.writeStringField("instructions", recipe.instructions);

        jsonGenerator.writeArrayFieldStart("ingredients");
        List<String > ingredients = recipe.getIngredients();
        for(String ingredient : ingredients){
            jsonGenerator.writeString(ingredient);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
