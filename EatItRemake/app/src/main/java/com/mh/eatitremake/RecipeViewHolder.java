package com.mh.eatitremake;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class RecipeViewHolder extends RecyclerView.ViewHolder{
    public TextView nameTextView;
    public TextView notesTextView;
    public RatingBar ratingBar;
    public ImageView recipeImage;


    public RecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.recipe_name);
        notesTextView = itemView.findViewById(R.id.recipe_notes);
        ratingBar = itemView.findViewById(R.id.ratingbar);
        recipeImage = itemView.findViewById(R.id.imageview_recipe);
    }
}
