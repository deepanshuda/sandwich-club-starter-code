package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.DetailActivity;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject sandwichObj = new JSONObject(json);
            Log.d(DetailActivity.DETAIL_TAG, sandwichObj.toString());

            Sandwich sandwich = new Sandwich();
            sandwich.setMainName(sandwichObj.getJSONObject("name").getString("mainName"));

            JSONArray alsoKnownArray = sandwichObj.getJSONObject("name").getJSONArray("alsoKnownAs");
            List<String> alsoKnownList = new ArrayList<>();

            for (int i = 0; i < alsoKnownArray.length(); i++) {
                alsoKnownList.add(alsoKnownArray.getString(i));
            }

            sandwich.setAlsoKnownAs(alsoKnownList);
            sandwich.setPlaceOfOrigin(sandwichObj.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichObj.getString("description"));
            sandwich.setImage(sandwichObj.getString("image"));

            JSONArray ingredientsArray = sandwichObj.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<>();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                ingredients.add(ingredientsArray.getString(i));
            }

            sandwich.setIngredients(ingredients);

            return sandwich;

        } catch (Throwable throwable) {
            Log.e(DetailActivity.DETAIL_TAG, "Could not parse malformed JSON");
        }

        return null;
    }
}
