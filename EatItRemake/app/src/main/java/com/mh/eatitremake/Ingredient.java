package com.mh.eatitremake;

public class Ingredient {
    private String name;
    private String pictureUrl;
    private Integer calories;

    public Ingredient(String name, String pictureUrl, Integer calories) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setNume(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
