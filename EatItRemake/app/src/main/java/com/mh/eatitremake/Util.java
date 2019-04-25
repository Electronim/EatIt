package com.mh.eatitremake;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Util {
    private static String DEFAULT_URL = "https://upload.wikimedia.org/wikipedia/commons/a/ac/No_image_available.svg";
    private static List<Recipe> data = new ArrayList<>();

    public static void parseCSV(AssetManager assetManager) {
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new InputStreamReader(assetManager.open("recipes.csv")));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] info = line.split(cvsSplitBy);

                String name = "";
                Integer rating = 0;
                String complexity = "";
                String notes = "";
                String type = "";
                Integer prepTime = 0;
                String pictureUrl = "";
                List<Ingredient> listOfIngredients = new ArrayList<>();

                for (int i = 0; i < info.length; i++) {
                    switch (i) {
                        case 0:
                            name = info[i];
                            break;
                        case 1:
                            rating = info[i].length();
                            break;
                        case 2:
                            complexity = info[i];
                            break;
                        case 3:
                            notes = info[i];
                            break;
                        case 4:
                            type = info[i];
                            break;
                        case 5:
                            if (info[i].length() == 0) {
                                prepTime = 0;
                            } else {
                                prepTime = Integer.parseInt(info[i]);
                            }
                            break;
                        case 6:
                            pictureUrl = getUrl(info[i]);
                            break;
                        case 9:
                            listOfIngredients = getIngredients(info[i]);
                            break;
                    }
                }

                Recipe recipe = new Recipe(name, rating, complexity, notes, type, prepTime, pictureUrl, listOfIngredients);
                data.add(recipe);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getUrl(String str) {
        boolean opened = false, closed = false;
        for (int i = 0; i < str.length(); i++) {
            opened |= (str.charAt(i) == '(');
            closed |= (str.charAt(i) == ')');
        }

        if (!opened || !closed) {
            return DEFAULT_URL;
        }

        String url = str;
        url = url.substring(url.indexOf("(") + 1);
        url = url.substring(0, url.indexOf(")"));

        return url;
    }

    private static List<Ingredient> getIngredients(String str) {
        List<Ingredient> ingredientList = new ArrayList<>();
        String[] ingredients = str.split(",");

        for (String ingredient: ingredients) {
            Ingredient newIngredient = new Ingredient(ingredient, DEFAULT_URL, 0);
            ingredientList.add(newIngredient);
        }

        return ingredientList;
    }

    public static List<Recipe> getData() {
        return data;
    }

    public static void setData(List<Recipe> data) {
        Util.data = data;
    }
}
