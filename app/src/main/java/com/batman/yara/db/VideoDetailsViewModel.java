package com.batman.yara.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VideoDetailsViewModel extends AndroidViewModel implements VideosDetailsDao {
    private VideoDetailsRepository repository;

    public VideoDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new VideoDetailsRepository(application);
    }

    @Override
    public void insert(ModelVideoDetailsDB video) {
        repository.insert(video);
    }

    @Override
    public void deleteAllDetails() {
        repository.deleteAllDetails();
    }

    @Override
    public LiveData<ModelVideoDetailsDB> getDetailsVideo(String imdbID) {
        return repository.getDetailsVideo(imdbID);
    }

    @Override
    public void update(ModelVideoDetailsDB video) {
        repository.update(video);
    }

    @Override
    public void delete(ModelVideoDetailsDB video) {
        repository.delete(video);
    }

}
