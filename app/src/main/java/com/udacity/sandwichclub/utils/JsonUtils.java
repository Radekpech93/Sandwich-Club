package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {


    //Implement the JSON parsing in JsonUtils so it produces a Sandwich Object that can be used to populate the UI that you designed.


    public static Sandwich parseSandwichJson(String json) {

        Sandwich parsedSandwich = new Sandwich();

        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
        final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        String mainName;
        String placeOfOrigin;
        String description;
        String image;

        List<String> akaList = new ArrayList<>();
        List<String> ingredientsList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(json);
            JSONObject name = root.getJSONObject(SANDWICH_NAME);

            //Get and set name of sandwich
            mainName = name.getString(SANDWICH_MAIN_NAME);

            //Get and set the place of origin
            placeOfOrigin = root.getString(SANDWICH_PLACE_OF_ORIGIN);

            //Get and set the description
            description = root.getString(SANDWICH_DESCRIPTION);

            //Get and set image URL
            image = root.getString(SANDWICH_IMAGE);


            //Get arrays for other names and ingredients

            JSONArray alsoKnownAs = null;
            JSONArray ingredients = null;

            if (name.getJSONArray(SANDWICH_ALSO_KNOWN_AS).length() > 0) {
                alsoKnownAs = name.getJSONArray(SANDWICH_ALSO_KNOWN_AS);

                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    String sandwichAlsoKnownAs = alsoKnownAs.get(i).toString();
                    akaList.add(sandwichAlsoKnownAs);
                }
            }

            if (root.getJSONArray(SANDWICH_INGREDIENTS).length() > 0) {
                ingredients = root.getJSONArray(SANDWICH_INGREDIENTS);

                for (int i = 0; i < ingredients.length(); i++) {
                    String sandwichIngredients = ingredients.get(i).toString();
                    ingredientsList.add(sandwichIngredients);
                }
            }


            return new Sandwich(mainName, akaList, placeOfOrigin, description, image, ingredientsList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
