package com.mh.eatitremake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DescriptionPopUp extends Activity {
    TextView recipeDescription;
    ImageView mImage;
    String recipeDescriptionText;
    String pictureUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_description);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * .8), (int)(height * .7));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setDimAmount(.75f);

        Animation a = AnimationUtils.loadAnimation(this, R.anim.animations);
        a.reset();

        recipeDescription = (TextView) findViewById(R.id.recipe_description);
        mImage = (ImageView) findViewById(R.id.recipe_image);

        Intent mIntent = getIntent();

        if (mIntent != null) {
            Bundle mBundle = mIntent.getExtras();
            recipeDescriptionText = (String) mBundle.get("RECIPE_DESCRIPTION");
            pictureUrl = (String) mBundle.get("RECIPE_URL");
            if (pictureUrl != null) {
                Picasso.get().load(pictureUrl).into(mImage);
            }

            if (recipeDescriptionText != null) {
                recipeDescription.setText(recipeDescriptionText);
            }
        }
    }
}
