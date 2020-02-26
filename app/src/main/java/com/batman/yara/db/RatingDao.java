package com.batman.yara.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface RatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModelRatingDB ratie);

    @Query("SELECT * FROM rating WHERE imdbID = :imdbID")
    LiveData<List<ModelRatingDB>> getAllRating(String imdbID);
}
