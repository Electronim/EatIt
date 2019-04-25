package com.mh.eatitremake;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecipeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipes;

    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRefChildren = myRef.child("recipes");

    private void populateRecipes() {
        myRefChildren.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot recipeDataSnapshot: dataSnapshot.getChildren()) {
                    Recipe recipe = recipeDataSnapshot.getValue(Recipe.class);

                    mRecipes.add(recipe);
                }

                mRecipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view, parent, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerview_recipes);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecipes = new ArrayList<>();
        populateRecipes();

        Log.d("List: ", mRecipes.toString());

        mRecipeAdapter = new RecipeAdapter(mRecipes);
        mRecyclerView.setAdapter(mRecipeAdapter);

        return rootView;
    }
}
