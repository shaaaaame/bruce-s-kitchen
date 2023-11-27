package data_access;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entity.GroceryList;

import java.io.IOException;
import java.util.Map;

public class GroceryListSerializer extends StdSerializer<GroceryList> {

    public GroceryListSerializer(){
        this(null);
    }

    public GroceryListSerializer(Class<GroceryList> gl){
        super(gl);
    }

    @Override
    public void serialize(GroceryList groceryList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("groceryId", groceryList.getGroceryId().toString());
        jsonGenerator.writeStringField("userId", groceryList.getUserId().toString());
        jsonGenerator.writeStringField("dateCreated", groceryList.getDate().toString());
        jsonGenerator.writeObjectFieldStart("ingredients");

        Map<String, String> ingredients = groceryList.getIngredients();
        for(String ingredient : ingredients.keySet()){
            jsonGenerator.writeStringField(ingredient, ingredients.get(ingredient).toString().replaceAll("\\P{Print}", ""));
        }

        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();
    }
}
