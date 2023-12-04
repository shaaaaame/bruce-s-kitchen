package data_access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import entity.Recipe;
import entity.RecipeFactory;
import use_case.recipe_search.RecipeSearchDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class InMemoryRecipeAPIDataAccessObject implements RecipeSearchDataAccessInterface {

    private final Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();
    private RecipeFactory recipeFactory;

    public InMemoryRecipeAPIDataAccessObject() throws IOException{
        this.recipeFactory = new RecipeFactory();

    }

    public List<Recipe> searchRecipe(String search) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(List.class, new RecipeDeserializer());
        objectMapper.registerModule(module);

        List<Recipe> recipeList = null;
        try {
            // Convert JSON string to List<Recipe>
            recipeList = objectMapper.readValue(apiCall(search), objectMapper.getTypeFactory().constructCollectionType(List.class, Recipe.class));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            addToRecipeMap(recipeList);
        }
        return recipeList;
    }

    public void addToRecipeMap(List<Recipe> recipeList){
        for (Recipe  recipe: recipeList){
            System.out.println(recipe.name);
            recipeMap.put(recipe.getRecipe_id(), recipe);
        }
    }

    @Override
    public boolean existsByID(UUID identifier) {
        return recipeMap.containsKey(identifier);
    }


    @Override
    public void save(Recipe recipe) {
        recipeMap.put(recipe.getRecipe_id(), recipe);
    }

    public static String apiCall(String myString){

        String search = myString.replaceAll("\\s", "%20");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.api-ninjas.com/v1/recipe?query=" + search))
                .header("X-Api-Key", "zgSAUkuV/RadzPi5Zmg/YQ==3CRbkHrEG4ZKiyhQ")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        String onion = "[{\"title\": \"Electronic Gourmet's French Onion Soup\", \"ingredients\": \"1 1/2 lb Yellow Onions, thinly sliced|(about 5 cups)|3 tb Butter|1 tb Oil|1 ts Salt|1/4 ts Sugar|3 tb Flour|8 c Boiling Brown Stock|1/2 c Dry White Wine or Vermouth|3 tb Cognac|Rounds of Hard Toasted Bread|2 c Swiss Cheese, grated\", \"servings\": \"6 Servings\", \"instructions\": \"1) Cook the onions slowly with the butter and oil in a covered saucepan for 15 minutes. 2) Uncover, raise the heat to moderate and stir in the salt and sugar. Cook for 30 to 40 minutes stirring frequently until the onions have turned and even, deep golden brown. 3) Sprinkle in the flour and stir 3 minutes. 4) Off heat, blend in the boiling liquid. Note that instead of brown stock you may use canned beef bouillon (same quantity). Or, and equal part of boiling water plus stock or bouillon. 5) Add the wine and season to taste. Simmer partially covered for 30 to 40 minutes or more, skimming occasionally. Correct Seasoning. 6) Set aside uncovered until ready to serve, then reheat to simmer. 7) Just before serving, stir in the cognac. Pour into french onion soup bowls over the rounds of bread. Cover with cheese. Bake at 350 F until cheese melts.\"}, {\"title\": \"Emeril's Onion Soup\", \"ingredients\": \"3 tb Unsalted butter|4 c Yellow; julienned|Salt|Freshly ground black pepper|1 tb Chopped garlic|1 ds Dry sherry|1 ds Worcestershire sauce|8 c Beef stock|8 sl Toasted french bread; (about 1-inch thick)|2 c Duck rillette; recipe follows|8 sl Gruyere cheese|2 ts Chopped fresh parsley leaves\", \"servings\": \"8 Servings\", \"instructions\": \"Preheat the oven to 400 degrees F. In a large saucepan, over medium-heat, melt the butter. Add the onions. Season with salt and pepper. Saut\\u00e9 the onions until slightly caramelized, about 8 to 10 minutes. Add the garlic, sherry, Worcestershire sauce and stock. Season with salt and pepper. Bring the liquid to a boil and reduce to a simmer. Cook the soup for 15 minutes. Ladle the soup into oven-proof deep bowls. Spread the each piece of bread with 1/4 cup of the rillette. Float the croutons on top of the soup. Cover each crouton with a piece of cheese. Sprinkle the cheese with parsley. Place the bowls on a sheet pan and place in the oven. Bake until the cheese is bubbly and golden, about 6 to 8 minutes. Remove from the oven and serve. Yield: 8 servings\"}, {\"title\": \"Endive, Beet and Red-Onion Salad\", \"ingredients\": \"1/2 lb Raw Beets, trimmed|3 Heads of Belgian Endives, md|1 Red onion|1 tb Dijon Mustard|1 tb Red Wine Vinegar|3 tb Vegetable oil|4 tb Finely Chopped Parsley\", \"servings\": \"4 Servings\", \"instructions\": \"Place the beets in a saucepan and add water to cover with salt. Bring to a boil until the beets are tender, about 30 minutes, depending on the size or age of the beets. Drain and let cool. Remove the skins and slice the beets. Trim off the bottom of the endives and cut them into 1 1/2 inch strips. Drop the pieces into cold water. Drain and pat dry. Peel and slice the onion. Combine the mustard, vinegar, salt and pepper in a salad bowl. Add the oil and blend well with a wire whisk. Add the beets, endive, onion and parsley. Toss well and serve. Serves 4. From The Gazette 90/12/12.\"}, {\"title\": \"Fresh Sweet Onion Rings with Basil\", \"ingredients\": \"1 lg Walla Walla Sweet Onion|1/2 c Seasoned Rice Vinegar, or|1/2 c Rice Vinegar with|1 ts Sugar|1 tb Fresh Basil, chopped|1/8 ts Dried Crushed Red Pepper|Flakes|1 c Small Ice Cubes|Salt\", \"servings\": \"6 Servings\", \"instructions\": \"Cut the onion crosswise into 1/4\\\" thick slices, seperate into rings. In a bowl, combine the onion rings, vinegar, basil, chili lfakes and ice; cover and chill 10-15 minutes, stirring occasionally. The ice will begin to melt, diluting the vinegar to an appropriate level. Serve, adding salt to taste. Calories: 23, Protein: 1g, Fat: .09g, Cholesterol: 5.5mg, Sodium: 1.83mg\"}, {\"title\": \"Fresh Tomato, Bacon and Onion Fettucine\", \"ingredients\": \"4 oz Uncooked fettuccine|3 Slices bacon|1/4 c Chopped onion|1 c (1 medium) chopped tomato|1 tb Chopped fresh basil OR|1/2 ts Dried basil leaves\", \"servings\": \"2 Servings\", \"instructions\": \"Cook fettuccine to desired doneness as directed on package. Drain, keep warm. Meanwhile, cook bacon in medium skillet until crisp; remove bacon and drain on paper towels. Reserve 1 Tbsp. drippings in skillet. Add onion; cook until crisp-tender. Crumble bacon; add to onion with tomato and basil. Cook just until heated. Immediately toss with cooked fettuccine. 4 side dish servings; 2 main dish servings.\"}, {\"title\": \"Fresh Tomato, Onion and Basil Salad\", \"ingredients\": \"Vine ripened firm tomatoes|Sliced|Your favorite slicing|Onions|Sliced and separated into|Rings|Fresh Basil -- chopped|Your favorite olive oil -- I|Like extra virgin|Your favorite wine vinegar|Salt and Pepper -- to taste\", \"servings\": \"1 Servings\", \"instructions\": \"Layer vegetables in a shallow dish. Rectangular containers work well. Distribute basil into layers. Add enough oil and vinegar to cover vegetables. Season with salt and pepper to taste. Cover and refrigerate for at least one hour to allow flavors to blend. This will keep for several days, especially if the tomatoes are nice and firm. : Supersew \"}, {\"title\": \"Fresh Tuna with Tangy Onions\", \"ingredients\": \"1 tb Olive oil|2 c Chopped onion|1/2 c Red wine vinegar|1/4 ts Salt, divided|1/4 ts Pepper, divided|1/2 c All-purpose flour|8 Tuna steaks, (4-ounce) (3/4 inch thick)|2 ts Olive oil|Mint sprigs, (optional)\", \"servings\": \"8 Servings\", \"instructions\": \"Heat 1 tablespoon oil in a large nonstick skillet over medium-high heat. Add onion, and saute 5 minutes or until lightly browned. Add vinegar, 1/8 teaspoon salt, and 1/8 teaspoon pepper, and cook for 2 minutes or until most of liquid evaporates. Remove the onion mixture from skillet; set aside, and keep warm. Wipe skillet clean with a paper towel. Combine the flour and remaining salt and pepper in a shallow dish, and stir well. Dredge tuna in flour mixture. Heat 2 teaspoons oil in skillet over medium heat. Add tuna, and cook for 2 minutes on each side until tuna is medium-rare or desired degree of doneness. Yield: 8 servings (serving size: 3 ounces fish and 2 tablespoons onion mixture). Per serving: 316 Calories; 11g Fat (33% calories from fat); 41g Protein; 10g Carbohydrate; 65mg Cholesterol; 134mg Sodium Serving Ideas : Garnish with mint sprigs, if desired. NOTES : Serve tuna with onion mixture.\"}, {\"title\": \"Fricassee De Poulet a la Poitevine (Chicken in Onion Sauce)\", \"ingredients\": \"1 kg (2 lb) onions|1 Chicken (1.5-2 kg [3-4 lb])|Salt, black pepper|25 g (1 oz) butter|2 tb Flour\", \"servings\": \"6 Servings\", \"instructions\": \"The regional recipes of France have evolved throughout time to complement the local wines. Hence the rich recipes of Burgundy, Poitou and Vendee produce wines of lesser glory and their recipes, in consequence, are more homely-but none the less appetizing when served with the 'little' wines of the region. Peel the onions, quarter them and slice thickly. Wipe the chicken inside and out with a cloth wrung out in boiling water. Cut into 10 serving pieces: 2 drumsticks, 2 thighs, 2 wings and 2 breasts halved. Season each piece on both sides. Melt the butter in a sauteuse or large frying pan over low heat and when foaming add the chicken and colour to the golden stage on both sides. Remove from the pan with a slotted spoon, cover and keep hot. Add the onions to the fats in the pan, season, increase the heat to medium, mix well and colour to the golden stage, moving them around with a wooden spatula to prevent sticking and colouring too fast. Sprinkle with half the flour and mix in thoroughly before adding the remainder. Cook for a few moments, stirring constantly, reduce the heat to low, add the vinegar, stir well and arrange the chicken on top. Cover and cook slowly for about 40 minutes until the chicken is tender and the moisture from the onions has made the sauce. During this time shake the pan frequently and do not lift the lid except to stir the ingredients twice. Correct the seasoning if necessary and serve very hot with plain boiled potatoes. Makes 6 servings.\"}, {\"title\": \"Fried Apples and Onions\", \"ingredients\": \"1/2 lb Bacon; sliced|2 lb Onions|2 lb Apples; tart, chopped|2 tb Sugar, brown\", \"servings\": \"6 Servings\", \"instructions\": \"Salt pork may be substituted for the bacon. Fry bacon slices in 12\\\" skillet until brown and crisp. Set aside on a warm serving platter. While meat is frying, peel onions, leaving stems to hold for slicing. To prevent your eyes from watering, hold a slice of bread in your teeth while you slice the onions as thin as possible. Discard stems. Core apples and cut crosswise in circles about 1/4\\\" thick. Apple skins help the slices hold their shape and add color to the dish, so don't peel unless skins are tough or scarred. Drain all but 1 tb fat from skillet, then add onion slices. Cook over medium-high heat about 3 minutes. Cover with apple slices in an even layer. Sprinkle brown sugar over all, cover skillet, & cook until tender, a few minutes more. Stir only to prevent scorching. Remove to warm plate with bacon slices.\"}, {\"title\": \"Goat Cheese And Braised Red Onion Tart\", \"ingredients\": \"2 tb Olive oil|3 Red onions; sliced into thin rounds|1 tb Red vinegar|1 c Red wine|1/2 tb Sugar|Salt; to taste|1/2 lb Puff pastry|4 oz Goat cheese; sliced|4 Tomatoes; peeled and seeded|6 Olives; pitted, chopped (6|To 8)|1/2 ts Parmesan cheese\", \"servings\": \"4 servings\", \"instructions\": \"Heat oil in a cast-iron skillet and saute the red onions until translucent. Stir in vinegar, red wine, sugar and salt. Place skillet in 375 degree oven for 1/2 hour. Cool. (Can be done 1 day ahead). Roll out puff pastry and cut out an 8 inch circle. Place on a pizza pan. Spread pastry with the braised onion mixture and top with goat cheese slices, tomatoes and olives. Sprinkle with Parmesan cheese. Place duck legs and shredded meat on top. Serve.\"}]\n";

        return response.body();
    }

}