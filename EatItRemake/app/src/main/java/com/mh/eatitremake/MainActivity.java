package com.mh.eatitremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ---- This one below is used to populate the database. DO NOT REMOVE! ----*/
        /* Util.parseCSV(getAssets());
        List<Recipe> recipeData = Util.getData();

        for (int i = 0; i < recipeData.size(); i++) {
            int id = 1000 + i;
            myRef.child("recipes").child(Integer.toString(id)).setValue(recipeData.get(i));
        } */
        /* ------------------------------------------------------------------------- */
    }
}
