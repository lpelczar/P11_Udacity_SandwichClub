package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /** Tag for the log messages */
    private static final String LOG_TAG = JsonUtils.class.getSimpleName();


    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {

            JSONObject sandwichObject = new JSONObject(json);

            JSONObject nameObject = sandwichObject.getJSONObject("name");

            String mainName = nameObject.optString("mainName");

            JSONArray alsoKnownAs = nameObject.getJSONArray("alsoKnownAs");

            List<String> alsoKnownList = new ArrayList<>();

            if (alsoKnownAs != null) {
                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    alsoKnownList.add(alsoKnownAs.getString(i));
                }
            }

            String placeOfOrigin = sandwichObject.optString("placeOfOrigin");

            String description = sandwichObject.optString("description");

            String imageUrl = sandwichObject.optString("image");

            JSONArray ingredients = sandwichObject.getJSONArray("ingredients");

            List<String> ingredientsList = new ArrayList<>();

            if (ingredients != null) {
                for (int i = 0; i < ingredients.length(); i++) {
                    ingredientsList.add(ingredients.getString(i));
                }
            }

            sandwich = new Sandwich(mainName, alsoKnownList, placeOfOrigin,
                                    description, imageUrl, ingredientsList);

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the JSON", e);
        }

        return sandwich;
    }
}
