package com.mh.eatitremake;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> recipesNames = new ArrayList<>();
    private ArrayAdapter<String> recipesNameAdapter;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRefChildren = myRef.child("recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        recipesNameAdapter =
            new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipesNames);

        /* ---- This one below is used to populate the database. DO NOT REMOVE! ----*/
        /* Util.parseCSV(getAssets());
        List<Recipe> recipeData = Util.getData();

        for (int i = 0; i < recipeData.size(); i++) {
            int id = 1000 + i;
            myRef.child("recipes").child(Integer.toString(id)).setValue(recipeData.get(i));
        } */
        /* ------------------------------------------------------------------------- */


        RecipeFragment details = new RecipeFragment();
        details.setArguments(getIntent().getExtras());
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.place_holder, details);
        ft.commit();

        populateRecipes();
        // System.out.println(details.getRecipes());

    }

    protected void populateRecipes() {
        myRefChildren.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeDataSnapshot: dataSnapshot.getChildren()) {
                    Recipe recipe = recipeDataSnapshot.getValue(Recipe.class);

                    recipesNames.add(recipe.getmName());
                }

                recipesNameAdapter.notifyDataSetChanged();

                AutoCompleteTextView editText = findViewById(R.id.actv);
                editText.setAdapter(recipesNameAdapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
