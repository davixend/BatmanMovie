package com.batman.yara.db.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.batman.yara.db.VideoRoomDatabase;
import com.batman.yara.db.dao.VideosDao;
import com.batman.yara.db.model.ModelVideoDB;

import java.util.List;

public class VideoRepository implements VideosDao {
    private VideosDao videosDao;
    private LiveData<List<ModelVideoDB>> mAllVideo;

    public VideoRepository(Application application) {
        VideoRoomDatabase db = VideoRoomDatabase.getDatabase(application);
        videosDao = db.videosDao();
        mAllVideo = videosDao.getAllVideo();
    }

    @Override
    public void insert(ModelVideoDB video) {
        new InsertAsyncTask(videosDao).execute(video);
    }

    @Override
    public void deleteAllVideo() {
        new DeleteAllAsyncTask(videosDao).execute();
    }

    @Override
    public void update(ModelVideoDB video) {
        new UpdateAsyncTask(videosDao).execute(video);
    }

    @Override
    public void delete(ModelVideoDB video) {
        new DeleteAsyncTask(videosDao).execute(video);
    }

    @Override
    public LiveData<List<ModelVideoDB>> getAllVideo() {
        return mAllVideo;
    }

    private static class InsertAsyncTask extends AsyncTask<ModelVideoDB, Void, Void> {
        private VideosDao videosDao;

        public InsertAsyncTask(VideosDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(ModelVideoDB... videos) {
            videosDao.insert(videos[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<ModelVideoDB, Void, Void> {
        private VideosDao videosDao;

        public UpdateAsyncTask(VideosDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(ModelVideoDB... videos) {
            videosDao.update(videos[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<ModelVideoDB, Void, Void> {
        private VideosDao videosDao;

        public DeleteAsyncTask(VideosDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(ModelVideoDB... videos) {
            videosDao.delete(videos[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private VideosDao videosDao;

        public DeleteAllAsyncTask(VideosDao videosDao) {
            this.videosDao = videosDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            videosDao.deleteAllVideo();
            return null;
        }
    }
}