package com.mh.eatitremake;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
import java.util.List;
import java.util.Set;

public class SelectIngredientActivity extends Activity {

    private ArrayAdapter<String> IngredientNameAdapter;
    private TextView mIngredientsTextView;
    private Set<Ingredient> result = new HashSet<>();
    private AutoCompleteTextView editText;
    private  Button btn;
    private Button finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ingredient);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height * .7));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setDimAmount(.75f);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.animations);
        a.reset();

        mIngredientsTextView = findViewById(R.id.ingredients_list_textview);

        IngredientNameAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getIngredientsNames());


        editText = findViewById(R.id.search_ingredients);
        editText.setAdapter(IngredientNameAdapter);


        // added search button
        btn = (Button) findViewById(R.id.search_button_ingredient);
        finishButton = (Button) findViewById(R.id.finish_ingredientlist_button);

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
                        mIngredientsTextView.setText(mIngredientsTextView.getText() + "\n -" + ingredient.getName());
                    }
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

