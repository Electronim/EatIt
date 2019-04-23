package com.mh.eatit;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mFab;
    static public FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab =  findViewById(R.id.fab);
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        FirebaseApp.initializeApp(getBaseContext());
        List<Ingredient> mlist = new ArrayList<>();
        mlist.add (new Ingredient("cartofi","not yet",30));
        mlist.add (new Ingredient("carnat","not yet",30));
        mlist.add (new Ingredient("branza","not yet",30));

        Recipe firstRecipe = new Recipe("Cartofi copti", 3, "easy","descriere","main dish",30,"deocamdata nu",mlist);
        myRef.child("recipes").child("1001").setValue(firstRecipe);

    }
}
