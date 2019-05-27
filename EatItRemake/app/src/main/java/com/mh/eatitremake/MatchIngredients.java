package com.mh.eatitremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MatchIngredients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_ingredients);

        Button testButton =  (Button) findViewById(R.id.TestButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchIngredients(new ArrayList<Ingredient>());
            }
        });
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

    protected List<RecipeCompatibility> matchIngredients(List<Ingredient> ingredients) {
        final RecipeFragment details = new RecipeFragment();
        details.setArguments(getIntent().getExtras());
        details.populateRecipes();

        List<RecipeCompatibility> recipeComp = new ArrayList<>();
        List<Recipe> allRecipies = details.getmDatabaseSaved();

        List<RecipeCompatibility> result = new ArrayList<>();

        





        return result;
    }
}
