package com.mh.eatitremake;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    List<Recipe> mRecipes;

    public RecipeAdapter(List<Recipe> mRecipes) {
        this.mRecipes = mRecipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View travelView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_layout, viewGroup, false);
        return new RecipeViewHolder(travelView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe currentRecipe = mRecipes.get(i);
        //Populate the viewholder based on the current Recipe
        recipeViewHolder.nameTextView.setText(currentRecipe.getmName());
        recipeViewHolder.notesTextView.setText(currentRecipe.getmComplexity() + " prep time:"+currentRecipe.getmPrepTime()+ " description: "+ currentRecipe.getmNotes());

        File imgFile = new File(currentRecipe.getPictureUrl());

        if (!currentRecipe.getPictureUrl().isEmpty())
            Picasso.get().load(currentRecipe.getPictureUrl()).into(recipeViewHolder.recipeImage);

    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
