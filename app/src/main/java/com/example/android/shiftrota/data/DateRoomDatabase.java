package com.example.android.shiftrota.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Database(entities = {Date.class}, version = 8)
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
                            .fallbackToDestructiveMigration()
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
            if (mDao.getAnyWord().length < 1) {
                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
                SimpleDateFormat format2 = new SimpleDateFormat("dd", Locale.ENGLISH);

                Calendar rightNow = Calendar.getInstance();
                String formatted2 = format2.format(rightNow.getTime());

                String formatted4 = format1.format(rightNow.getTime());
                Date date = new Date(formatted4, 0);
                mDao.insert(date);

                Calendar rightMeow = Calendar.getInstance();
                String formatted3 = format2.format(rightMeow.getTime());

                int mein = Integer.parseInt(formatted2);

                int klein = Integer.parseInt(formatted3);

                /* Total amount of days from today; till the end of the current month. */
                int test = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);

                for (int i = mein; i > 1; i--) {
                    rightNow.add(Calendar.DATE, -1);
                    String formatted = format1.format(rightNow.getTime());
                    date.setDate(formatted);
                    mDao.insert(date);
                }

                for (int i = klein; i < test; i++) {
                    rightMeow.add(Calendar.DATE, 1);
                    String formatted = format1.format(rightMeow.getTime());
                    date.setDate(formatted);
                    mDao.insert(date);
                }
            }
            return null;
        }
    }
}
