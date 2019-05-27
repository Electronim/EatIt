package com.mh.eatitremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectIngredientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ingredient);
    }
//
//        IngredientNameAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recipesNames);
//
//
//        populateSearchRecipeNames();
//
//        // added search button
//        Button btn = (Button) findViewById(R.id.search_button_ingredient);
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                EditText searchText = (EditText) findViewById(R.id.search_ingredients);
//                String recipeName = searchText.getText().toString();
//
//                List<Recipe> recipes = details.getmDatabaseSaved();
//                List<Recipe> result = new ArrayList<>();
//                for (Recipe recipe: recipes) {
//                    if (recipeName.length() == 0) {
//                        result.add(recipe);
//                    } else if (recipe.getmName().equals(recipeName)) {
//                        result.add(recipe);
//                    }
//                }
//
//                details.setmRecipes(result);
//            }
//
//        });
//
//
//    }

//    protected void populateSearchRecipeNames() {
//        myRefChildren.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot recipeDataSnapshot: dataSnapshot.getChildren()) {
//                    Recipe recipe = recipeDataSnapshot.getValue(Recipe.class);
//
//                    recipesNames.add(recipe.getmName());
//                }
//
//                recipesNameAdapter.notifyDataSetChanged();
//
//                AutoCompleteTextView editText = findViewById(R.id.actv);
//                editText.setAdapter(recipesNameAdapter);
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {}
//        });
//    }


}

