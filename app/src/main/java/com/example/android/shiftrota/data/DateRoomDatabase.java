package com.example.android.shiftrota.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Date.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DateRoomDatabase extends RoomDatabase {

    public abstract DateDao dateDao();

    /*Make the DateRoomDatabase a singleton to prevent having multiple instances of the database
     opened at the same time */
    private static volatile DateRoomDatabase INSTANCE;

    static DateRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DateRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DateRoomDatabase.class, "date_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DateDao mDao;

        PopulateDbAsync(DateRoomDatabase db) {
            mDao = db.dateDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            mDao.deleteAll();

//            Date date = new Date("Klein");
//            mDao.insert(date);
//
//            Date date1 = new Date("Mein");
//            mDao.insert(date1);
//            word = new Word("World");
//            mDao.insert(word);
//            DatesGenerator.getDays();
            return null;
        }
    }
}
