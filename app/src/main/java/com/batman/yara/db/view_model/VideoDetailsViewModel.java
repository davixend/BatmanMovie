package com.batman.yara.db.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.batman.yara.db.dao.VideosDetailsDao;
import com.batman.yara.db.model.ModelVideoDetailsDB;
import com.batman.yara.db.repository.VideoDetailsRepository;

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
