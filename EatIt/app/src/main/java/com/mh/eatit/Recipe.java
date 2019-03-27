package com.mh.eatit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.widget.RatingBar;

import java.util.List;

@Entity
public class Recipe {
    @PrimaryKey
    private String mName;
    @ColumnInfo(name = "rating_bar")
    private RatingBar mRatingBar;
    @ColumnInfo(name = "complexity")
    private String mComplexity;
    @ColumnInfo(name = "notes")
    private String mNotes;
    @ColumnInfo(name = "type")
    private String mType;
    @ColumnInfo(name = "prep_time")
    private int mPrepTime;
    @ColumnInfo(name = "picture_url")
    private String pictureUrl;
    @ColumnInfo(name = "ingerdient_list")
    private List<Ingredient> mIngredients;
    @ColumnInfo(name = "quantity_vector")
    private List<Integer> mQuantities;

    public Recipe (){}
    public Recipe(String mName, RatingBar mRatingBar, String mComplexity, String mNotes, String mType, int mPrepTime, String pictureUrl, List<Ingredient> mIngredients) {
        this.mName = mName;
        this.mRatingBar = mRatingBar;
        this.mComplexity = mComplexity;
        this.mNotes = mNotes;
        this.mType = mType;
        this.mPrepTime = mPrepTime;
        this.pictureUrl = pictureUrl;
        this.mIngredients = mIngredients;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public RatingBar getmRatingBar() {
        return mRatingBar;
    }

    public void setmRatingBar(RatingBar mRatingBar) {
        this.mRatingBar = mRatingBar;
    }

    public String getmComplexity() {
        return mComplexity;
    }

    public void setmComplexity(String mComplexity) {
        this.mComplexity = mComplexity;
    }

    public String getmNotes() {
        return mNotes;
    }

    public void setmNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public int getmPrepTime() {
        return mPrepTime;
    }

    public void setmPrepTime(int mPrepTime) {
        this.mPrepTime = mPrepTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Ingredient> getmIngredients() {
        return mIngredients;
    }

    public void setmIngredients(List<Ingredient> mIngredients) {
        this.mIngredients = mIngredients;
    }

    public List<Integer> getmQuantities() {
        return mQuantities;
    }

    public void setmQuantities(List<Integer> mQuantities) {
        this.mQuantities = mQuantities;
    }
}
