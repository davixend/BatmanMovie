package com.batman.yara.db.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.batman.yara.db.dao.RatingDao;
import com.batman.yara.db.model.ModelRatingDB;
import com.batman.yara.db.repository.RatingRepository;

import java.util.List;

public class RatingViewModel extends AndroidViewModel implements RatingDao {
    private RatingRepository repository;

    public RatingViewModel(@NonNull Application application) {
        super(application);
        repository = new RatingRepository(application);
    }

    @Override
    public void insert(ModelRatingDB ratie) {
        repository.insert(ratie);
    }

    @Override
    public LiveData<List<ModelRatingDB>> getAllRating(String imdbID) {
        return repository.getAllRating(imdbID);
    }

}
