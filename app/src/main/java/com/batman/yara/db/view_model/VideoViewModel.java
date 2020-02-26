package com.batman.yara.db.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.batman.yara.db.dao.VideosDao;
import com.batman.yara.db.model.ModelVideoDB;
import com.batman.yara.db.repository.VideoRepository;

import java.util.List;

public class VideoViewModel extends AndroidViewModel implements VideosDao {
    private VideoRepository repository;
    private LiveData<List<ModelVideoDB>> mAllVideo;

    public VideoViewModel(@NonNull Application application) {
        super(application);
        repository = new VideoRepository(application);
        mAllVideo = repository.getAllVideo();
    }

    @Override
    public void insert(ModelVideoDB video) {
        repository.insert(video);
    }

    @Override
    public void deleteAllVideo() {
        repository.deleteAllVideo();
    }

    @Override
    public void update(ModelVideoDB video) {
        repository.update(video);
    }

    @Override
    public void delete(ModelVideoDB video) {
        repository.delete(video);
    }

    @Override
    public LiveData<List<ModelVideoDB>> getAllVideo() {
        return repository.getAllVideo();
    }
}
