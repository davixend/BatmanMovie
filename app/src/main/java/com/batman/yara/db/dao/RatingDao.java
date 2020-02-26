package com.batman.yara.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.batman.yara.db.model.ModelRatingDB;

import java.util.List;


@Dao
public interface RatingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModelRatingDB ratie);

    @Query("SELECT * FROM rating WHERE imdbID = :imdbID")
    LiveData<List<ModelRatingDB>> getAllRating(String imdbID);
}
