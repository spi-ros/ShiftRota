package com.example.android.shiftrota.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateRepository {

    private DateDao mDateDao;
    private LiveData<List<Date>> mAllDates, nAllDates;

    private SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
    //        SimpleDateFormat format3 = new SimpleDateFormat("MM", Locale.ENGLISH);
    private Calendar rightNow = Calendar.getInstance();
    private String stringFormat = format1.format(rightNow.getTime());
    private String search;
    private String search1;
    private int year = Integer.parseInt(DatesGenerator.lastTwo(stringFormat));
    private String month = DatesGenerator.firstTwo(stringFormat);

    /*constructor that gets a handle to the database and initializes the member variables.*/

    public DateRepository(Application application) {
        DateRoomDatabase db = DateRoomDatabase.getDatabase(application);
        mDateDao = db.dateDao();

        switch (month) {
            case "01": {
                rightNow.set(year, Calendar.JANUARY, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightNow.set(year, Calendar.JANUARY, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "02": {
                rightNow.set(year, Calendar.FEBRUARY, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightNow.set(year, Calendar.FEBRUARY, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "03": {
                rightNow.set(year, Calendar.MARCH, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightNow.set(year, Calendar.MARCH, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "04": {
                rightNow.set(year, Calendar.APRIL, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightNow.set(year, Calendar.APRIL, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "05": {
                rightNow.set(year, Calendar.MAY, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.MAY, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "06": {
                rightNow.set(year, Calendar.JUNE, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.JUNE, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "07": {
                rightNow.set(year, Calendar.JULY, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.JULY, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "08": {
                rightNow.set(year, Calendar.AUGUST, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.AUGUST, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "09": {
                rightNow.set(year, Calendar.SEPTEMBER, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.SEPTEMBER, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "10": {
                rightNow.set(year, Calendar.OCTOBER, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.OCTOBER, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "11": {
                rightNow.set(year, Calendar.NOVEMBER, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.NOVEMBER, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case "12": {
                rightNow.set(year, Calendar.DECEMBER, 1);
                search = format1.format(rightNow.getTime());
                int totalOfDaysInMonth = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH) + 1;
                rightNow.set(year, Calendar.DECEMBER, totalOfDaysInMonth);
                search1 = format1.format(rightNow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
        }
    }

    /* Room executes all queries on a separate thread.
     Observed LiveData will notify the observer when the data has changed.*/

    public LiveData<List<Date>> loadByMonth() {
        return mAllDates;
    }

    public LiveData<List<Date>> loadNextMonth() {
        return nAllDates;
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
