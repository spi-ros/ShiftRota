package com.example.android.shiftrota.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateRepository {

    private DateDao mDateDao;
    private LiveData<List<String>> klein;
    private LiveData<List<String>> mein;
    private LiveData<List<Date>> mAllDates;
    public LiveData<List<String>> mAllHoursByMonth;

    /*constructor that gets a handle to the database and initializes the member variables.*/

    public DateRepository(Application application) {
        DateRoomDatabase db = DateRoomDatabase.getDatabase(application);
        mDateDao = db.dateDao();
    }

//    public List<Date> statusUpdtae() {
//        klein = mDateDao.statusUpdate(Integer.parseInt(DatesGenerator.yesterdayM()), Integer.parseInt(DatesGenerator.todayM()));
//        return klein;
//    }

    public LiveData<List<String>> getWorkedHours(int months) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format11 = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        String stringFormats = format11.format(rightNow.getTime());
        int years = Integer.parseInt(DatesGenerator.lastFour(stringFormats));

        switch (months) {
            case 1: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JANUARY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JANUARY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 2: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.FEBRUARY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.FEBRUARY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 3: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.MARCH, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.MARCH, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 4: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.APRIL, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.APRIL, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 5: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.MAY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.MAY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 6: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JUNE, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JUNE, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 7: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JULY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JULY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 8: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.AUGUST, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.AUGUST, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 9: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.SEPTEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
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
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 11: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.NOVEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.NOVEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
            case 12: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.DECEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.DECEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                klein = mDateDao.klein(search, search1);
                break;
            }
        }
        return klein;
    }

    public LiveData<List<String>> getBookedHours(int months) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format11 = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        String stringFormats = format11.format(rightNow.getTime());
        int years = Integer.parseInt(DatesGenerator.lastFour(stringFormats));

        switch (months) {
            case 1: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JANUARY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JANUARY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 2: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.FEBRUARY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.FEBRUARY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 3: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.MARCH, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.MARCH, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 4: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.APRIL, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.APRIL, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 5: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.MAY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.MAY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 6: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JUNE, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JUNE, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 7: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.JULY, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.JULY, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 8: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.AUGUST, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.AUGUST, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 9: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.SEPTEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 10: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.OCTOBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.OCTOBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 11: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.NOVEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.NOVEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
            case 12: {
                Calendar rightMeow = Calendar.getInstance();
                rightMeow.set(years, Calendar.DECEMBER, 1);
                String search = format11.format(rightMeow.getTime());
                int totalOfDaysInMonth = rightMeow.getActualMaximum(Calendar.DAY_OF_MONTH);
                rightMeow.set(years, Calendar.DECEMBER, totalOfDaysInMonth);
                String search1 = format11.format(rightMeow.getTime());
                mein = mDateDao.mein(search, search1);
                break;
            }
        }
        return mein;
    }

    public LiveData<List<Date>> getMonth(int month) {
        Log.d("DateRepository", "month = " + month);

        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format11 = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        String stringFormats = format11.format(rightNow.getTime());
        int years = Integer.parseInt(DatesGenerator.lastFour(stringFormats));

        switch (month) {
            case 1: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.JANUARY, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(2018, Calendar.DECEMBER - 1, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(2018, Calendar.DECEMBER, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(2018, Calendar.DECEMBER, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(2018, Calendar.DECEMBER, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(2018, Calendar.DECEMBER, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(2018, Calendar.DECEMBER, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.JANUARY, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.JANUARY, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 2: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.FEBRUARY, 1);
                etsi.getTime();
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.FEBRUARY, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.FEBRUARY, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.FEBRUARY, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.FEBRUARY, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.FEBRUARY, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.FEBRUARY, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.FEBRUARY, 1);
                    search = format11.format(etsi.getTime());
                }
                etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth);
                Log.d("DATEREPOSITORY", "totalDaysInMonth " + totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    if (totalOfDaysInMonth == 28) {
                        etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 14);
                        search1 = format11.format(etsi.getTime());
                    } else {
                        etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 7);
                        search1 = format11.format(etsi.getTime());
                    }
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 12);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 13);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 3: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.MARCH, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.MARCH, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.MARCH, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.MARCH, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.MARCH, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.MARCH, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.MARCH, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.MARCH, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.MARCH, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.MARCH, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 4: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.APRIL, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.APRIL, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.APRIL, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.APRIL, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.APRIL, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.APRIL, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.APRIL, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.APRIL, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.APRIL, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 12);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.APRIL, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 5: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.MAY, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.MAY, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.MAY, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.MAY, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.MAY, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.MAY, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.MAY, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.MAY, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.MAY, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.MAY, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 6: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.JUNE, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.JUNE, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.JUNE, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.JUNE, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.JUNE, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.JUNE, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.JUNE, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.JUNE, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.JUNE, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 12);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.JUNE, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 7: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.JULY, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.JULY, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.JULY, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.JULY, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.JULY, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.JULY, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.JULY, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.JULY, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.JULY, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.JULY, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 8: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.AUGUST, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.AUGUST, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.AUGUST, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.AUGUST, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.AUGUST, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.AUGUST, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.AUGUST, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.AUGUST, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.AUGUST, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 9: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.SEPTEMBER, 1);
                etsi.getTime();
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.SEPTEMBER, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.SEPTEMBER, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.SEPTEMBER, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.SEPTEMBER, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.SEPTEMBER, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.SEPTEMBER, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.SEPTEMBER, 1);
                    search = format11.format(etsi.getTime());
                }
                etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 12);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 10: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.OCTOBER, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.OCTOBER, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.OCTOBER, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.OCTOBER, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.OCTOBER, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.OCTOBER, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.OCTOBER, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.OCTOBER, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 11: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.NOVEMBER, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.NOVEMBER, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.NOVEMBER, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.NOVEMBER, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.NOVEMBER, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.NOVEMBER, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.NOVEMBER, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.NOVEMBER, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 12);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
                break;
            }
            case 12: {
                String search;
                String search1;
                int etsiTesting;
                Calendar etsi = Calendar.getInstance();
                etsi.set(years, Calendar.DECEMBER, 1);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                Log.d("DATEREPOSITORY", "ETSI TESTING " + etsiTesting);

                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.DECEMBER, 1 - 6);
                    search = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.DECEMBER, 1 - 5);
                    search = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.DECEMBER, 1 - 4);
                    search = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.DECEMBER, 1 - 3);
                    search = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.DECEMBER, 1 - 2);
                    search = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years, Calendar.DECEMBER, 0);
                    search = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.DECEMBER, 1);
                    search = format11.format(etsi.getTime());
                }
                int totalOfDaysInMonth = etsi.getActualMaximum(Calendar.DAY_OF_MONTH);
                etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth);
                etsi.getTime();
                etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
                /*Sunday*/
                if (etsiTesting == 1) {
                    etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 7);
                    search1 = format11.format(etsi.getTime());
                    /*Saturday*/
                } else if (etsiTesting == 7) {
                    etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 8);
                    search1 = format11.format(etsi.getTime());
                    /*Friday*/
                } else if (etsiTesting == 6 ) {
                    etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 9);
                    search1 = format11.format(etsi.getTime());
                    /*Thursday*/
                } else if (etsiTesting == 5) {
                    etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 10);
                    search1 = format11.format(etsi.getTime());
                    /*Wednesday*/
                } else if (etsiTesting == 4) {
                    etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 11);
                    search1 = format11.format(etsi.getTime());
                    /*Tuesday*/
                } else if (etsiTesting == 3) {
                    etsi.set(years + 1, Calendar.DECEMBER + 1, totalOfDaysInMonth + 5);
                    search1 = format11.format(etsi.getTime());
                    /*Monday*/
                } else {
                    etsi.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 6);
                    search1 = format11.format(etsi.getTime());
                }
                mAllDates = mDateDao.loadByMonth(search, search1);
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
