package com.example.android.shiftrota.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

class DateRepositoryHelper {

    private static SimpleDateFormat format11 = new SimpleDateFormat("yyyy/MM/dd", Locale.UK);

    static String[] searchStringsJanuary(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.JANUARY, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.JANUARY, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.JANUARY, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.JANUARY, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.JANUARY, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.JANUARY, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.JANUARY, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.JANUARY, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.JANUARY, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.JANUARY, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsFebruary(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.FEBRUARY, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.FEBRUARY, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.FEBRUARY, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.FEBRUARY, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.FEBRUARY, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.FEBRUARY, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.FEBRUARY, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.FEBRUARY, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.FEBRUARY, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            if (totalOfDaysInMonth == 28) {
                etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 14);
                string2 = format11.format(etsi1.getTime());
            } else {
                etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 7);
                string2 = format11.format(etsi1.getTime());
            }
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 12);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.FEBRUARY, totalOfDaysInMonth + 13);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsMarch(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.MARCH, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.MARCH, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.MARCH, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.MARCH, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.MARCH, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.MARCH, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.MARCH, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.MARCH, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.MARCH, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.MARCH, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsApril(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.APRIL, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.APRIL, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.APRIL, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.APRIL, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.APRIL, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.APRIL, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.APRIL, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.APRIL, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.APRIL, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 12);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.APRIL, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsMay(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.MAY, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.MAY, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.MAY, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.MAY, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.MAY, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.MAY, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.MAY, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.MAY, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.MAY, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.MAY, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.MAY, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsJune(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.JUNE, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.JUNE, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.JUNE, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.JUNE, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.JUNE, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.JUNE, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.JUNE, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.JUNE, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.JUNE, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 12);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.JUNE, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsJuly(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.JULY, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.JULY, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.JULY, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.JULY, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.JULY, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.JULY, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.JULY, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.JULY, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.JULY, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.JULY, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.JULY, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsAugust(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.AUGUST, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.AUGUST, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.AUGUST, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.AUGUST, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.AUGUST, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.AUGUST, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.AUGUST, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.AUGUST, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.AUGUST, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.AUGUST, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsSeptember(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.SEPTEMBER, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.SEPTEMBER, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.SEPTEMBER, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.SEPTEMBER, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.SEPTEMBER, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.SEPTEMBER, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.SEPTEMBER, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.SEPTEMBER, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.SEPTEMBER, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 12);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.SEPTEMBER, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsOctober(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.OCTOBER, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.OCTOBER, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.OCTOBER, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.OCTOBER, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.OCTOBER, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.OCTOBER, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.OCTOBER, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.OCTOBER, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.OCTOBER, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.OCTOBER, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsNovember(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.NOVEMBER, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.NOVEMBER, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.NOVEMBER, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.NOVEMBER, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.NOVEMBER, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.NOVEMBER, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.NOVEMBER, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.NOVEMBER, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.NOVEMBER, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 12);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.NOVEMBER, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }

    static String[] searchStringsDecember(int years) {
        String string1;
        String string2;
        int etsiTesting;
        Calendar etsi = Calendar.getInstance();
        etsi.set(years, Calendar.DECEMBER, 1);
        etsi.getTime();
        etsiTesting = etsi.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting == 1) {
            etsi.set(years, Calendar.DECEMBER, 1 - 6);
            string1 = format11.format(etsi.getTime());
            /*Saturday*/
        } else if (etsiTesting == 7) {
            etsi.set(years, Calendar.DECEMBER, 1 - 5);
            string1 = format11.format(etsi.getTime());
            /*Friday*/
        } else if (etsiTesting == 6) {
            etsi.set(years, Calendar.DECEMBER, 1 - 4);
            string1 = format11.format(etsi.getTime());
            /*Thursday*/
        } else if (etsiTesting == 5) {
            etsi.set(years, Calendar.DECEMBER, 1 - 3);
            string1 = format11.format(etsi.getTime());
            /*Wednesday*/
        } else if (etsiTesting == 4) {
            etsi.set(years, Calendar.DECEMBER, 1 - 2);
            string1 = format11.format(etsi.getTime());
            /*Tuesday*/
        } else if (etsiTesting == 3) {
            etsi.set(years, Calendar.DECEMBER, 0);
            string1 = format11.format(etsi.getTime());
            /*Monday*/
        } else {
            etsi.set(years, Calendar.DECEMBER, 1);
            string1 = format11.format(etsi.getTime());
        }
        Calendar etsi1 = Calendar.getInstance();
        etsi1.set(years, Calendar.DECEMBER, 1);
        int totalOfDaysInMonth = etsi1.getActualMaximum(Calendar.DAY_OF_MONTH);
        etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth);
        etsi1.getTime();
        int etsiTesting1 = etsi1.get(Calendar.DAY_OF_WEEK);
        /*Sunday*/
        if (etsiTesting1 == 1) {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 7);
            string2 = format11.format(etsi1.getTime());
            /*Saturday*/
        } else if (etsiTesting1 == 7) {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 8);
            string2 = format11.format(etsi1.getTime());
            /*Friday*/
        } else if (etsiTesting1 == 6) {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 9);
            string2 = format11.format(etsi1.getTime());
            /*Thursday*/
        } else if (etsiTesting1 == 5) {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 10);
            string2 = format11.format(etsi1.getTime());
            /*Wednesday*/
        } else if (etsiTesting1 == 4) {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 11);
            string2 = format11.format(etsi1.getTime());
            /*Tuesday*/
        } else if (etsiTesting1 == 3) {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 5);
            string2 = format11.format(etsi1.getTime());
            /*Monday*/
        } else {
            etsi1.set(years, Calendar.DECEMBER, totalOfDaysInMonth + 6);
            string2 = format11.format(etsi1.getTime());
        }
        return new String[] {string1, string2};
    }
}
