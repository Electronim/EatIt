package com.mh.eatitremake;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SelectIngredientActivity extends AppCompatActivity {

    private ArrayAdapter<String> IngredientNameAdapter;
    private TextView mIngredientsTextView;
    private Set<Ingredient> result = new LinkedHashSet<>();
    private AutoCompleteTextView editText;
    private  Button btn, removeButton;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_ingredient);
        mIngredientsTextView = findViewById(R.id.ingredients_list_textview);

        IngredientNameAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getIngredientsNames());


        editText = findViewById(R.id.search_ingredients);
        editText.setAdapter(IngredientNameAdapter);


        // added search button
        btn = (Button) findViewById(R.id.search_button_ingredient);
        finishButton = (Button) findViewById(R.id.finish_ingredientlist_button);
        removeButton = (Button) findViewById(R.id.remove_last_ingredient);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                EditText searchText = (EditText) findViewById(R.id.search_ingredients);
                String ingredientName = searchText.getText().toString();

                editText.setText("");

                List<Ingredient> ingredients = new ArrayList<>(MainMenuActivity.mGlobalIngredients);
                for (Ingredient ingredient: ingredients) {
                    if (ingredient.getName().equals(ingredientName) && !result.contains(ingredient)) {
                        result.add(ingredient);
                        mIngredientsTextView.setText(mIngredientsTextView.getText() + " - " + ingredient.getName() + "\n");
                    }
                }

            }

        });

        removeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (!result.isEmpty()) {
                    Ingredient aux = null;
                    for (Ingredient ingredient : result) {
                        aux = ingredient;
                    }
                    result.remove(aux);
                    int length = mIngredientsTextView.getText().toString().lastIndexOf('-');
                    mIngredientsTextView.setText(mIngredientsTextView.getText().toString().substring(0, length));
                }
            }

        });



    }

    public List<String> getIngredientsNames(){
        List<String> mIngredientsNames = new ArrayList<>();
        for (Ingredient ingredient: MainMenuActivity.mGlobalIngredients){
            mIngredientsNames.add(ingredient.getName());
        }
        return mIngredientsNames;
    }

    public void finishIngredientListOnClick(View v){
        Intent mIntent = getIntent();
        mIntent.putExtra("IngredientList",new ArrayList<Ingredient>(result));
        setResult(Activity.RESULT_OK,mIntent);
        finish();
    }

}

