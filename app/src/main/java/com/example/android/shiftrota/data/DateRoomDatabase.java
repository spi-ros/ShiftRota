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

@Database(entities = {Date.class}, version = 20)
public abstract class DateRoomDatabase extends RoomDatabase {

    /*Make the DateRoomDatabase a singleton to prevent having multiple instances of the database
     opened at the same time */
    private static volatile DateRoomDatabase INSTANCE;
    private static int MY_MONTH = 0;
    private static int MY_DAY = 1;
    private static Calendar rightNow = Calendar.getInstance();
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

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
//            mDao.deleteAll();
            if (mDao.getAnyWord().length < 1) {
                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);

                String stringFormat = format1.format(rightNow.getTime());
                int year = Integer.parseInt(DatesGenerator.lastTwo(stringFormat));

                rightNow.set(year, MY_MONTH, MY_DAY);

                for (int i = 0; i < 13; i++) {

                    rightNow.add(Calendar.DATE, 0);
                    String formatted = format1.format(rightNow.getTime());
                    Date date = new Date(formatted, 0, null, null);
                    mDao.insert(date);

                    int test = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);

                    for (int j = 1; j < test; j++) {
                        rightNow.add(Calendar.DATE, 1);
                        String formatted2 = format1.format(rightNow.getTime());
                        Date date1 = new Date(formatted2, 0, null, null);
                        mDao.insert(date1);
                    }
                    rightNow.set(year, MY_MONTH++, MY_DAY);
                }
            }
            return null;
        }
    }
}
