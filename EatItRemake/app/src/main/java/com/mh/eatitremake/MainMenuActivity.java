package com.mh.eatitremake;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainMenuActivity extends AppCompatActivity {

    public static List<Recipe> mDatabaseSaved =  new ArrayList<>();
    public static Set<Ingredient> mGlobalIngredients;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRefChildren = myRef.child("recipes");

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);

        Button browse_button = findViewById(R.id.browse_recipes);
        browse_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, MainActivity.class));
            }
        });

        Button ingredientsSelection = findViewById(R.id.ingredients_selection);
        ingredientsSelection.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, MatchIngredients.class));
            }
        });
      
        Button add_new_recipe_button = findViewById(R.id.add_new_recipe);
        add_new_recipe_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, NewRecipeActivity.class));
            }
        });

        Button rate_application = findViewById(R.id.rate);
        rate_application.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, RateApplicationPopUpActivity.class));
            }
        });

        populateRecipes();
    }

    private void populateRecipes() {
        myRefChildren.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeDataSnapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = recipeDataSnapshot.getValue(Recipe.class);

                    mDatabaseSaved.add(recipe);
                }

                findAllIngredients();
                Log.d("ingredient", "test");
                for( Ingredient ingredient: mGlobalIngredients){
                    Log.d("ingredient", ingredient.getName());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void findAllIngredients(){
        mGlobalIngredients = new HashSet<>();
        for (Recipe recipe: mDatabaseSaved) {
            if(recipe.getmIngredients() != null)
                for (Ingredient ingredient : recipe.getmIngredients()) {
                    mGlobalIngredients.add(ingredient);
                }
        }
    }
}


