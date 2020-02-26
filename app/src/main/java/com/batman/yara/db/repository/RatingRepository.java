package com.batman.yara.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.batman.yara.db.VideoRoomDatabase;
import com.batman.yara.db.dao.RatingDao;
import com.batman.yara.db.model.ModelRatingDB;

import java.util.List;

public class RatingRepository implements RatingDao {
    private RatingDao ratingDao;

    public RatingRepository(Application application) {
        VideoRoomDatabase db = VideoRoomDatabase.getDatabase(application);
        ratingDao = db.ratingDao();
    }

    @Override
    public void insert(ModelRatingDB video) {
        new InsertAsyncTask(ratingDao).execute(video);
    }

    @Override
    public LiveData<List<ModelRatingDB>> getAllRating(String imdbID) {
        return ratingDao.getAllRating(imdbID);
    }


    private static class InsertAsyncTask extends AsyncTask<ModelRatingDB, Void, Void> {
        private RatingDao ratingDao;

        public InsertAsyncTask(RatingDao ratingDao) {
            this.ratingDao = ratingDao;
        }

        @Override
        protected Void doInBackground(ModelRatingDB... videos) {
            ratingDao.insert(videos[0]);
            return null;
        }
    }
}