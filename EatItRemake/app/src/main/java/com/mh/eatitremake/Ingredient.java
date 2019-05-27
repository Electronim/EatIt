package com.mh.eatitremake;

import android.os.Build;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable {
    private String name;
    private String pictureUrl;
    private Integer calories;

    public Ingredient() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.hash(name);
        }
        return Integer.valueOf(name);
    }
}
