package data_access;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entity.Recipe;

import java.io.IOException;
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
        jsonGenerator.writeStringField("userId", recipe.getUserId().toString());
        jsonGenerator.writeStringField("dateCreated", recipe.getDate().toString());
        jsonGenerator.writeObjectFieldStart("ingredients");

        Map<String, String> ingredients = recipe.getIngredients();
        for(String ingredient : ingredients.keySet()){
            jsonGenerator.writeStringField(ingredient, ingredients.get(ingredient).replaceAll("\\P{Print}", ""));
        }

        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
