package com.batman.yara.db;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.batman.yara.db.dao.RatingDao;
import com.batman.yara.db.dao.VideosDao;
import com.batman.yara.db.dao.VideosDetailsDao;
import com.batman.yara.db.model.ModelRatingDB;
import com.batman.yara.db.model.ModelVideoDB;
import com.batman.yara.db.model.ModelVideoDetailsDB;

@Database(entities = {ModelVideoDB.class, ModelVideoDetailsDB.class, ModelRatingDB.class}, version = 4)
public abstract class VideoRoomDatabase extends RoomDatabase {

    private static volatile VideoRoomDatabase INSTANCE;

    public abstract VideosDao videosDao();
    public abstract VideosDetailsDao videosDetailsDao();
    public abstract RatingDao ratingDao();

    public static synchronized VideoRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    VideoRoomDatabase.class, "video_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return INSTANCE;
    }

    private static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.e("feawefawef", "fawefawefawef");
            new PopulatedDbAsyncTask(INSTANCE.videosDao()).execute();
        }
    };

    private static class PopulatedDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private VideosDao userDao;

        public PopulatedDbAsyncTask(VideosDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            userDao.insert(new ModelVideoDB("fff", "132", "123","sdfa","af"));
//            Log.e("aefdawefawef", userDao.getAllVideo().getValue().get(0).getTitle());
            return null;
        }
    }

}
