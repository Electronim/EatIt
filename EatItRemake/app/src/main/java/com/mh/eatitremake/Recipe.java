package com.mh.eatitremake;

import java.util.List;

public class Recipe {
    private String mName;
    private Integer mRatingBar;
    private String mComplexity;
    private String mNotes;
    private String mType;
    private Integer mPrepTime;
    private String pictureUrl;
    private List<Ingredient> mIngredients;
    private List<Integer> mQuantities;

    public Recipe(){}
    public Recipe(String mName, Integer mRatingBar, String mComplexity, String mNotes, String mType, Integer mPrepTime, String pictureUrl, List<Ingredient> mIngredients) {
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

    public Integer getmRatingBar() {
        return mRatingBar;
    }

    public void setmRatingBar(Integer mRatingBar) {
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

    public Integer getmPrepTime() {
        return mPrepTime;
    }

    public void setmPrepTime(Integer mPrepTime) {
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
