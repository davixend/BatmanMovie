package com.batman.yara.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.batman.yara.db.model.ModelVideoDB;

import java.util.List;


@Dao
public interface VideosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ModelVideoDB videos);

    @Query("DELETE FROM videos")
    void deleteAllVideo();

    @Delete
    void delete(ModelVideoDB video);

    @Query("SELECT * FROM videos ORDER BY id")
    LiveData<List<ModelVideoDB>> getAllVideo();

    @Update
    void update(ModelVideoDB video);
}
