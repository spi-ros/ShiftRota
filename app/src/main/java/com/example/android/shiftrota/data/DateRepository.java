package com.example.android.shiftrota.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import android.os.AsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateRepository {

    private DateDao mDateDao;
    private LiveData<List<Date>> mAllDates;
    private Calendar rightNow = Calendar.getInstance();
    private SimpleDateFormat format11 = new SimpleDateFormat("yyyy/MM/dd", Locale.UK);
    private String stringFormats = format11.format(rightNow.getTime());
    private int years = Integer.parseInt(DatesGenerator.firstFour(stringFormats));

    /*constructor that gets a handle to the database and initializes the member variables.*/

    public DateRepository(Application application) {
        DateRoomDatabase db = DateRoomDatabase.getDatabase(application);
        mDateDao = db.dateDao();
    }

    public void setYears(int years) {
        this.years = years;
    }

    public LiveData<List<Date>> getWorkedHours(int months) {

        switch (months) {
            case 1: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JANUARY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JANUARY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 2: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.FEBRUARY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.FEBRUARY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 3: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.MARCH, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.MARCH, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 4: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.APRIL, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.APRIL, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 5: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.MAY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.MAY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 6: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JUNE, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JUNE, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 7: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JULY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JULY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 8: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.AUGUST, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.AUGUST, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 9: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.SEPTEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 10: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(2019, Calendar.OCTOBER, 1);
                rightMeow.getTime();
//                Log.d("DATEREPOSITORY", "CALENDAR" + rightMeow);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.OCTOBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 11: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.NOVEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.NOVEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 12: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.DECEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.DECEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
        }
        return mAllDates;
    }

    public LiveData<List<Date>> getMonth(int month) {

        switch (month) {
            case 1: {
                String[] string = DateRepositoryHelper.searchStringsJanuary(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 2: {
                String[] string = DateRepositoryHelper.searchStringsFebruary(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 3: {
                String[] string = DateRepositoryHelper.searchStringsMarch(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 4: {
                String[] string = DateRepositoryHelper.searchStringsApril(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 5: {
                String[] string = DateRepositoryHelper.searchStringsMay(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 6: {
                String[] string = DateRepositoryHelper.searchStringsJune(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 7: {
                String[] string = DateRepositoryHelper.searchStringsJuly(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 8: {
                String[] string = DateRepositoryHelper.searchStringsAugust(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 9: {
                String[] string = DateRepositoryHelper.searchStringsSeptember(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 10: {
                String[] string = DateRepositoryHelper.searchStringsOctober(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 11: {
                String[] string = DateRepositoryHelper.searchStringsNovember(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
            case 12: {
                String[] string = DateRepositoryHelper.searchStringsDecember(years);
                mAllDates = mDateDao.loadByMonth(string[0], string[1]);
                break;
            }
        }
        return mAllDates;
    }
    /* Room executes all queries on a separate thread.
     Observed LiveData will notify the observer when the data has changed.*/

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
