package data_access;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entity.GroceryList;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
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
        jsonGenerator.writeStringField("name", groceryList.getName().toString());
        jsonGenerator.writeStringField("groceryId", groceryList.getGroceryId().toString());
        jsonGenerator.writeStringField("userId", groceryList.getUserId() != null ? groceryList.getUserId().toString() : null);
        jsonGenerator.writeStringField("dateCreated", groceryList.getDate().toString());

        jsonGenerator.writeArrayFieldStart("ingredients");
        List<String > ingredients = groceryList.getIngredients();
        for(String ingredient : ingredients){
            jsonGenerator.writeString(ingredient);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
