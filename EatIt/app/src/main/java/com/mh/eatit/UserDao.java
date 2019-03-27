package com.mh.eatit;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM recipe")
    List<Recipe> getAll();

    @Query("SELECT * FROM recipe WHERE mName IN (:userIds)")
    List<Recipe> loadAllByName(String[] userIds);

    @Query("SELECT * FROM recipe WHERE mName LIKE :name ")
    Recipe findByName(String name);

    @Insert
    void insertAll(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);
}
