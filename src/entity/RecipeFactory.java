package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public class RecipeFactory {
    public Recipe create(UUID recipe_id, UUID user_id, String name, String servings, List<String> ingredients, Tag[] tags, String instructions, LocalDateTime dateCreated){
        return new Recipe(recipe_id, user_id, name, servings, ingredients, tags, instructions, dateCreated);
    }
    // If it is API call, please set UUID to null :)))))

    public Recipe create(UUID user_id, String name, String servings, List<String> ingredients, Tag[] tags, String instructions, LocalDateTime dateCreated){
        return new Recipe(user_id, name, servings, ingredients, tags, instructions, dateCreated);
    }
}
