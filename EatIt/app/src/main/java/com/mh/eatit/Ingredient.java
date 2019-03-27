package com.mh.eatit;

public class Ingredient {
    private String nume;
    private String pictureUrl;
    private Integer calories;

    public Ingredient(String nume, String pictureUrl, Integer calories) {
        this.nume = nume;
        this.pictureUrl = pictureUrl;
        this.calories = calories;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
