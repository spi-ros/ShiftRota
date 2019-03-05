package com.example.android.shiftrota.data;



import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DatesGenerator {

    private static Calendar rightNow = Calendar.getInstance();

    private static SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
    private static SimpleDateFormat format2 = new SimpleDateFormat("dd", Locale.ENGLISH);
    private static String formatted2 = format2.format(rightNow.getTime());
    private static int mein = Integer.parseInt(formatted2);

    public DatesGenerator(Application application) {}

    private final static List<String> daysIncrement = new ArrayList<String>() {{

        /* Total amount of days, of the current month. */
        int test = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i=mein; i<test; i++) {
            rightNow.add(Calendar.DATE, 1);
            String formatted = format1.format(rightNow.getTime());
            add(formatted);
        }
    }};

    private final static List<String> daysDecrement = new ArrayList<String>() {{

        for (int i=mein; i>1; i--) {
            rightNow.add(Calendar.DATE, -1);
            String formatted = format1.format(rightNow.getTime());
            add(formatted);
        }
    }};

    private final static List<String> days = new ArrayList<String>() {{

        String formatted = format1.format(rightNow.getTime());
        add(formatted);
        addAll(daysIncrement);
        addAll(daysDecrement);
    }};

    public static List<String> getDays() {

        Collections.sort(days);
        return days;
    }
}
