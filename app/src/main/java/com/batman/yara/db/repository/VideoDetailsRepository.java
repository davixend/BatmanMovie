package com.batman.yara.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.batman.yara.db.VideoRoomDatabase;
import com.batman.yara.db.dao.VideosDetailsDao;
import com.batman.yara.db.model.ModelVideoDetailsDB;

public class VideoDetailsRepository implements VideosDetailsDao {
    private VideosDetailsDao videosDetailsDao;

    public VideoDetailsRepository(Application application) {
        VideoRoomDatabase db = VideoRoomDatabase.getDatabase(application);
        videosDetailsDao = db.videosDetailsDao();
    }

    @Override
    public void insert(ModelVideoDetailsDB video) {
        new InsertAsyncTask(videosDetailsDao).execute(video);
    }

    @Override
    public void deleteAllDetails() {
        new DeleteAllAsyncTask(videosDetailsDao).execute();
    }

    @Override
    public LiveData<ModelVideoDetailsDB> getDetailsVideo(String imdbID) {
         return videosDetailsDao.getDetailsVideo(imdbID);
    }

    @Override
    public void update(ModelVideoDetailsDB video) {
        new UpdateAsyncTask(videosDetailsDao).execute(video);
    }

    @Override
    public void delete(ModelVideoDetailsDB video) {
        new DeleteAsyncTask(videosDetailsDao).execute(video);
    }


    private static class InsertAsyncTask extends AsyncTask<ModelVideoDetailsDB, Void, Void> {
        private VideosDetailsDao videosDao;

        public InsertAsyncTask(VideosDetailsDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(ModelVideoDetailsDB... videos) {
            videosDao.insert(videos[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<ModelVideoDetailsDB, Void, Void> {
        private VideosDetailsDao videosDao;

        public UpdateAsyncTask(VideosDetailsDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(ModelVideoDetailsDB... videos) {
            videosDao.update(videos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<ModelVideoDetailsDB, Void, Void> {
        private VideosDetailsDao videosDao;

        public DeleteAsyncTask(VideosDetailsDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(ModelVideoDetailsDB... videos) {
            videosDao.delete(videos[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private VideosDetailsDao videosDao;

        public DeleteAllAsyncTask(VideosDetailsDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            videosDao.deleteAllDetails();
            return null;
        }
    }
}