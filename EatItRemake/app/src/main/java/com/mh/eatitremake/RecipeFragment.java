package com.mh.eatitremake;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecipeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipes;


    private void populateRecipes() {
        mRecipes.addAll(MainMenuActivity.mDatabaseSaved);
        mRecipeAdapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_view, parent, false);

        mRecyclerView = rootView.findViewById(R.id.recyclerview_recipes);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mRecipes = new ArrayList<>();
        mRecipeAdapter = new RecipeAdapter(mRecipes);

        populateRecipes();

        Log.d("List: ", mRecipes.toString());


        mRecyclerView.setAdapter(mRecipeAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(),
                mRecyclerView, new ContactClickListener() {
            @Override
            public void onClick(View view, final int position) {
                Log.d("CLICK_TAG", "am dat click pe positia: " + position);
                // TODO button click inside of recycler view
                Recipe recipe = mRecipes.get(position);
                Intent mIntent = new Intent(view.getContext(), DescriptionPopUp.class);
                mIntent.putExtra("RECIPE_DESCRIPTION",recipe.getmNotes());
                mIntent.putExtra("RECIPE_URL",recipe.getPictureUrl());
                startActivity(mIntent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
        return rootView;
    }

    public void notifyChanges() {
        mRecipeAdapter.notifyDataSetChanged();
    }

    public List<Recipe> getRecipes(){
        return mRecipes;
    }

    public RecipeAdapter getmRecipeAdapter() {
        return mRecipeAdapter;
    }

    public void setmRecipes(List<Recipe> recipes) {
       mRecipes.clear();
       mRecipes.addAll(recipes);
       mRecipeAdapter.notifyDataSetChanged();
    }
}
