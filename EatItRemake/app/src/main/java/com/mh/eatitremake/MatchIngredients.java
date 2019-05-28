package com.mh.eatitremake;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MatchIngredients extends AppCompatActivity {
    private int FIXED_SIZE = 20;
    private int KEY_CODE = 42;
    private RecipeFragment details = null;
    private android.support.v4.app.FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_match_ingredients);

        details = new RecipeFragment();
        details.setArguments(getIntent().getExtras());
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.matched_recipes, details);
        ft.commit();

        Button testButton =  (Button) findViewById(R.id.TestButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(MatchIngredients.this, SelectIngredientActivity.class);
                startActivityForResult(mIntent, KEY_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == KEY_CODE){ //daca e adevarat, intentul vine de la ingrediente
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>) data.getExtras().getSerializable("IngredientList");
                List<Recipe> resultedRecipes = matchIngredients(ingredientList);

                details.setmRecipes(resultedRecipes);
            }
        }
    }

    class RecipeCompatibility {
        private Recipe recipe;
        private double compatibility;

        public RecipeCompatibility(Recipe recipe, double compatibility) {
            this.recipe = recipe;
            this.compatibility = compatibility;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }

        public double getCompatibility() {
            return compatibility;
        }

        public void setCompatibility(double compatibility) {
            this.compatibility = compatibility;
        }
    }

    protected List<Recipe> matchIngredients(List<Ingredient> ingredients) {

        List<RecipeCompatibility> recipeComp = new ArrayList<>();
        List<Recipe> allRecipies = MainMenuActivity.mDatabaseSaved;

        List<Recipe> result = new ArrayList<>();

        for (Recipe recipe : allRecipies) {
            recipeComp.add(new RecipeCompatibility(recipe, 0.0));
        }

        for (RecipeCompatibility recipe: recipeComp) {
            if (recipe.getRecipe().getmIngredients() == null) {
                continue;
            }

            for (Ingredient ingE: recipe.getRecipe().getmIngredients()) {
                for (Ingredient ingS: ingredients) {
                    String existent = ingE.getName().toLowerCase();
                    String searched = ingS.getName().toLowerCase();

                    if (!existent.equals(searched) ||
                            recipe.getRecipe().getmIngredients().size() == 0) {
                        continue;
                    }

                    double modifiedCompatibility = recipe.compatibility;
                    modifiedCompatibility += 1.0 / recipe.getRecipe().getmIngredients().size();
                    recipe.compatibility = modifiedCompatibility;
                }
            }
        }

        Collections.sort(recipeComp, new Comparator<RecipeCompatibility>() {
            @Override
            public int compare(RecipeCompatibility o1, RecipeCompatibility o2) {
                if (o1.getCompatibility() > o2.getCompatibility())
                    return -1;
                if (o1.getCompatibility() < o2.getCompatibility())
                    return 1;
                return 0;
            }
        });

        for (int i = 0; i < FIXED_SIZE; ++i) {
            if (recipeComp.get(i).getCompatibility() > 0.0) {
                result.add(recipeComp.get(i).getRecipe());
            }
        }

        return result;
    }
}
