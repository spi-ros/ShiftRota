package com.example.android.shiftrota.data;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Database(entities = {Date.class}, version = 21)
public abstract class DateRoomDatabase extends RoomDatabase {

    /*Make the DateRoomDatabase a singleton to prevent having multiple instances of the database
     opened at the same time */
    private static volatile DateRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    static DateRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DateRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DateRoomDatabase.class, "date_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract DateDao dateDao();

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final DateDao mDao;

        PopulateDbAsync(DateRoomDatabase db) {
            mDao = db.dateDao();
        }

        void iterateBetweenDatesJava7(Calendar start, Calendar end) {

            while (start.before(end)) {

                start.add(Calendar.DATE, 1);
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
                String formatted = format1.format(start.getTime());
                Date date = new Date(formatted, 0,null, null, "00:00", null, null);
                mDao.insert(date);
            }
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            mDao.deleteAll();
            if (mDao.getAnyWord().length < 1) {


                Calendar startCalendar = Calendar.getInstance();
                startCalendar.set(2000, 0, 1);
                startCalendar.getTime();

                Calendar endCalendar = Calendar.getInstance();
                endCalendar.set(2070, 11, 31);
                endCalendar.getTime();
                iterateBetweenDatesJava7(startCalendar, endCalendar);
            }
            return null;
        }
    }
}
