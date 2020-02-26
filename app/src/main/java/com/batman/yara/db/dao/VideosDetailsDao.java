package com.batman.yara.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.batman.yara.db.model.ModelVideoDetailsDB;


@Dao
public interface VideosDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModelVideoDetailsDB videos);

    @Query("DELETE FROM details")
    void deleteAllDetails();

    @Query("SELECT * FROM details WHERE imdbID = :imdbID LIMIT 1")
    LiveData<ModelVideoDetailsDB> getDetailsVideo(String imdbID);

    @Delete
    void delete(ModelVideoDetailsDB video);

    @Update
    void update(ModelVideoDetailsDB video);
}
