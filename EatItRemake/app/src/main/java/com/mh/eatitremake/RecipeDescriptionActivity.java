package com.mh.eatitremake;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RecipeDescriptionActivity extends AppCompatActivity {
    TextView recipeDescription;
    ImageView mImage;
    String recipeDescriptionText;
    String pictureUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_description);
        Animation a = AnimationUtils.loadAnimation(this, R.anim.animations);
        a.reset();
        recipeDescription = (TextView) findViewById(R.id.recipe_description);
        mImage = (ImageView) findViewById(R.id.recipe_image);


        Intent mIntent = getIntent();

        if (mIntent != null){
            Bundle mBundle = mIntent.getExtras();
            recipeDescriptionText = (String) mBundle.get("RECIPE_DESCRIPTION");
            pictureUrl = (String) mBundle.get("RECIPE_URL");
            if (pictureUrl != null)
                Picasso.get().load(pictureUrl).into(mImage);
            if (recipeDescriptionText != null)
                recipeDescription.setText(recipeDescriptionText);

        }
    }

}
