package com.mh.eatitremake;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission_group.CAMERA;

public class NewRecipeActivity extends AppCompatActivity {
    private static final String TAG = "NEWRECIPETAG";
    private static final Integer KEY_CODE = 98;
    Button addRecipeButton;
    Button addImageButon;
    EditText recipeName;
    EditText recipeDescription;
    EditText recipePrepTime;
    RatingBar recipeRating;
    RadioGroup complexityGroup;
    ImageView imageView;
    Uri picUri;
    Spinner typeSpinner;

    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();
    private final static int ALL_PERMISSIONS_RESULT = 107;
    private ArrayList<Ingredient> recipeIngredients = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_recipe);
        InitView();

        addImageButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getPickImageChooserIntent(), 200);

            }
        });
        permissions.add(CAMERA);
        permissionsToRequest = findUnAskedPermissions(permissions);
        //get the permissions we have asked for before but are not granted..
        //we will store this in a global list to access later.


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionsToRequest != null) {
            Log.d(TAG, "Testez permisiunile");
            if (permissionsToRequest.size() > 0)
                requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
        }

    }
    private void InitView(){
        addRecipeButton = findViewById(R.id.add_recipe_button);
        addImageButon = findViewById(R.id.add_image_button);
        recipeName = findViewById(R.id.recipe_name_edittext);
        recipeRating = findViewById(R.id.recipe_ratingBar);
        recipeDescription = findViewById(R.id.recipe_input_description_edittext);
        complexityGroup = findViewById(R.id.complexity_radio_group);
        recipePrepTime = findViewById(R.id.recipe_preptime_edittext);
        imageView = findViewById(R.id.recipe_imageview);

        //Iniatilize and populate the spinner
        typeSpinner = findViewById(R.id.recipe_type_spinner);
        //Create the adapter for the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recipe_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Map the adapter to the spinner
        typeSpinner.setAdapter(adapter);

    }

    public void addRecipeOnClick (View v){
        String name = recipeName.getText().toString();
        String complexity= null;
        String type = typeSpinner.getSelectedItem().toString();
        Integer rating = recipeRating.getNumStars();
        String description = recipeDescription.getText().toString();
        Integer prepTime = null;
        if (recipePrepTime.getText().length()>0)
            prepTime = Integer.parseInt(recipePrepTime.getText().toString());

        int id  = (int)complexityGroup.getCheckedRadioButtonId();
        switch (id){
            case (0):
                complexity = "Hard";
                break;
            case (1):
                complexity= "Medium";
                break;
            case (2):
                complexity = "Easy";
                break;
        }
        if (name != null && rating != null && complexity != null && description != null && type != null && prepTime != null && picUri != null && recipeIngredients != null )
        {Recipe newRecipe = new Recipe(name,rating,complexity,description,type,prepTime,picUri.toString(),recipeIngredients);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRefChildren = myRef.child("recipes");
        myRef.child("recipes").child(Integer.toString(MainMenuActivity.mDatabaseSaved.size()+1000)).setValue(newRecipe);
//        MainMenuActivity.mDatabaseSaved.add(newRecipe);

        Intent tempIntent = new Intent (NewRecipeActivity.this,MainMenuActivity.class);
        startActivity(tempIntent);
        }
        else{
            Toast.makeText(getBaseContext(), "Fill in all the fields!", Toast.LENGTH_LONG).show();
        }
    }

    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = (Intent) allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == KEY_CODE){ //daca e adevarat, intentul vine de la ingrediente
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>) data.getExtras().getSerializable("IngredientList");
                recipeIngredients = new ArrayList<>(ingredientList);
                Log.d(TAG,ingredientList.toString());
                for (Ingredient ingredient : ingredientList){
                    recipeDescription.setText("-"+ingredient.getName()+"\n"+recipeDescription.getText());
                }
            }
        }
        else // altfel, intentul vine de la camera
        if (resultCode == Activity.RESULT_OK) {
            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);
                Log.d(TAG, picUri.toString());
                Picasso.get().load(picUri).into(imageView);
            }

        }
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("pic_uri", picUri);
    }

    private ArrayList findUnAskedPermissions(ArrayList wanted) {
        ArrayList result = new ArrayList();

        for (Object perm : wanted) {
            if (!hasPermission((String) perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    public void addIngredientsOnClick(View view) {
        Intent mIntent = new Intent(NewRecipeActivity.this,SelectIngredientActivity.class);
        startActivityForResult(mIntent, KEY_CODE);
    }
}
