package com.example.android.shiftrota.data;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.selection.SelectionTracker;

import com.example.android.shiftrota.R;
import com.example.android.shiftrota.UI.DateViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class DatesGenerator {

    private static String monthString;

    static public String today() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy", Locale.UK);
        return format1.format(calendar.getTime());
    }

    static public String todayTheOtherWay() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd", Locale.UK);
        return format1.format(calendar.getTime());
    }

    static private String todayM() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MMddyy", Locale.UK);
        return format1.format(calendar.getTime());
    }

    static public String yesterdayM() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Calendar.MONTH, Integer.parseInt(firstTwo(todayM())));
//        int year = Calendar.getInstance().get(Calendar.YEAR);
//        int month = Calendar.
        SimpleDateFormat format1 = new SimpleDateFormat("MMddyy", Locale.UK);
        return format1.format(calendar.getTime());
    }

    static private String firstTwo(String string) {
        return string.substring(0, 2);
    }

    static public String lastTwo(String string) {
        return string.substring(8);
    }

    static public String midTwo(String string) {
        return string.substring(5, 7);
    }

    static public String firstFour(String string) {
        return string.substring(0, 4);
    }

    static public int getMonthInt() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MM", Locale.UK);
        String stringFormat = format1.format(rightNow.getTime());
        return Integer.parseInt(stringFormat);
    }

    private static String formatting(long s) {
        if (s < 10) return "0" + s;
        else return "" + s;
    }

    static public String hoursWorkedString (long workedLong) {
        long hhWorked01 = workedLong / 3600;
        workedLong %= 3600;
        long mmWorked01 = workedLong / 60;
        return formatting(hhWorked01) + " Hours and " + formatting(mmWorked01) + " Minutes";
    }

    static public String getMonthString() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MM", Locale.UK);
        return format1.format(rightNow.getTime());
    }

    static public Calendar getToday() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.getTime();
        return rightNow;
    }

    static public Calendar getCalendarFromString(String string) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd", Locale.UK);
        try {
            rightNow.setTime(Objects.requireNonNull(format1.parse(string)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rightNow;
    }

    /**
     * Changing date from "yyyy/MM/dd" to "dd/MM/yy"
     */
    static public String normalDate(String string) {
        char[] c = string.toCharArray();
        char[] data = {c[8], c[9], c[7], c[5], c[6]};
        return new String(data);
    }

    static public String normalHour(String string) {
        String data = null;
        if (string != null) {
            char[] c = string.toCharArray();
            data = c[1] + c[2] + ":" + c[3];
        }
        return data;
    }

    static public String nameOfMonth(int monthInt) {
        if (monthInt == 1) {
            monthString = "January";
        }
        if (monthInt == 2) {
            monthString = "February";
        }
        if (monthInt == 3) {
            monthString = "March";
        }
        if (monthInt == 4) {
            monthString = "April";
        }
        if (monthInt == 5) {
            monthString = "May";
        }
        if (monthInt == 6) {
            monthString = "June";
        }
        if (monthInt == 7) {
            monthString = "July";
        }
        if (monthInt == 8) {
            monthString = "August";
        }
        if (monthInt == 9) {
            monthString = "September";
        }
        if (monthInt == 10) {
            monthString = "October";
        }
        if (monthInt == 11) {
            monthString = "November";
        }
        if (monthInt == 12) {
            monthString = "December";
        }
        return monthString;
    }

    static public void saveInit(int statusChangedValue, String hoursChangedValue,
                                List<String> testList, DateViewModel mDateViewModel,
                                SelectionTracker tracker, Context context, Resources resources) {

        //        String notesString = Objects.requireNonNull(notesEditText.getText()).toString().trim();

        for (int i = 0; i < testList.size(); i++) {
            Date date;
            if (statusChangedValue == 1 && DatesGenerator.getCalendarFromString(testList.get(i)).
                    before(DatesGenerator.getToday())) {
                Toast.makeText(context, resources.
                                getString(R.string.cant_book_in_the_past),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(context, resources.
                                getString(R.string.change_status_or_date_in_future),
                        Toast.LENGTH_SHORT).show();
            } else if (statusChangedValue == 2 && DatesGenerator.getCalendarFromString(testList.get(i)).
                    after(DatesGenerator.getToday())) {
                Toast.makeText(context, resources.
                                getString(R.string.cant_book_in_the_future),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(context, resources.
                                getString(R.string.change_status_or_date_in_past),
                        Toast.LENGTH_SHORT).show();
            } else if ((statusChangedValue == 1 || statusChangedValue == 2) &&
                    (hoursChangedValue.isEmpty() || hoursChangedValue.matches("0") ||
                            hoursChangedValue.matches("00"))) {
                Toast.makeText(context, "Please give the amount of hours",
                        Toast.LENGTH_SHORT).show();
            } else if (statusChangedValue == 0) {
                for (int j = 0; j < testList.size(); j++) {
                    date = new Date(testList.get(j), statusChangedValue, "00:00", null);
                    mDateViewModel.insert(date);
                }
            } else if (statusChangedValue == 3) {
                for (int j = 0; j < testList.size(); j++) {
                    date = new Date(testList.get(j), statusChangedValue, "00:00", null);
                    mDateViewModel.insert(date);
                }
            } else {
                for (int j = 0; j < testList.size(); j++) {
                    date = new Date(testList.get(j), statusChangedValue, hoursChangedValue, null);
                    mDateViewModel.insert(date);
                }
            }
        }
        if (tracker.hasSelection()) {
            tracker.clearSelection();
        }
    }


    static public void layoutAnimation(RelativeLayout relativeLayout, boolean visibility) {

        // get the center for the clipping will_work_circle
        int cx = relativeLayout.getWidth() / 2;
        int cy = relativeLayout.getHeight() / 2;

        // get the initial radius for the clipping will_work_circle
        float initialRadius = (float) Math.hypot(cx, cy);

        // create the animation (the final radius is zero)
        if (visibility) {
            Animator anim;
            anim = ViewAnimationUtils.createCircularReveal(relativeLayout, cx, cy, 0f, initialRadius);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            });
            anim.setDuration(550);
            anim.start();
        }
        if (!visibility) {
            Animator anim;
            anim = ViewAnimationUtils.createCircularReveal(relativeLayout, cx, cy, initialRadius, 0f);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    relativeLayout.setVisibility(View.INVISIBLE);
                }
            });
            anim.setDuration(550);
            anim.start();
        }

    }
}
