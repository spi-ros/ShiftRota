package com.example.android.shiftrota.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class DateRepository {

    private DateDao mDateDao;
    private LiveData<List<Date>> mAllDates;

    /*constructor that gets a handle to the database and initializes the member variables.*/

    public DateRepository(Application application) {
        DateRoomDatabase db = DateRoomDatabase.getDatabase(application);
        mDateDao = db.dateDao();
        mAllDates = mDateDao.getAllDates();
    }

    /* Room executes all queries on a separate thread.
     Observed LiveData will notify the observer when the data has changed.*/

    public LiveData<List<Date>> getAllDates() {
        return mAllDates;
    }

    /*This should get called from a non-UI thread or the app will crash.
    * Room ensures that we don't do any long running operations on the main thread,
    * blocking the UI.*/

    public void insert(Date date) {
        new insertAsyncTask(mDateDao).execute(date);
    }

    private static class insertAsyncTask extends AsyncTask<Date, Void, Void> {

        private DateDao mAsyncTaskDao;

        insertAsyncTask(DateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Date... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
