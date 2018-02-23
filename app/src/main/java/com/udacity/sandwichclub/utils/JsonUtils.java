package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class  JsonUtils {

    private static final String NAME_JSON = "name";
    private static final String MAIN_NAME_JSON = "mainName";
    private static final String ALSO_KNOWN_JSON = "alsoKnownAs";
    private static final String ORIGIN_JSON = "placeOfOrigin";
    private static final String DESCRIPTION_JSON = "description";
    private static final String IMAGE_JSON = "image";
    private static final String INGREDIENTS_JSON = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {

        Sandwich sandwich = new Sandwich();

        JSONObject jsonObject = new JSONObject(json);
        JSONObject name = jsonObject.getJSONObject(NAME_JSON);

        String mainName = name.getString(MAIN_NAME_JSON);
        JSONArray alsoKnownArray = name.getJSONArray(ALSO_KNOWN_JSON);

        List<String> alsoKnownAsList = new ArrayList<>();

        for (int i = 0; i < alsoKnownArray.length(); i++) {
            alsoKnownAsList.add(alsoKnownArray.getString(i));
        }

        String placeOfOrigin = jsonObject.getString(ORIGIN_JSON);

        String description = jsonObject.getString(DESCRIPTION_JSON);

        String image = jsonObject.getString(IMAGE_JSON);

        JSONArray ingredientsArray = jsonObject.getJSONArray(INGREDIENTS_JSON);

        List<String> ingredientsList = new ArrayList<>();

        for (int i = 0; i<ingredientsArray.length(); i++) {
            ingredientsList.add(ingredientsArray.getString(i));
        }

        sandwich.setMainName(mainName);
        sandwich.setPlaceOfOrigin(placeOfOrigin);
        sandwich.setAlsoKnownAs(alsoKnownAsList);
        sandwich.setDescription(description);
        sandwich.setImage(image);
        sandwich.setIngredients(ingredientsList);

        return sandwich;
    }
}
