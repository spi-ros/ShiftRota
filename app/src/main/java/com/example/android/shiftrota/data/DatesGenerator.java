package com.example.android.shiftrota.data;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DatesGenerator {

    private static String monthString;

    static public String today() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy", Locale.UK);
        return format1.format(calendar.getTime());
    }

    static public String todayM() {
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

    static public String firstTwo(String string) {
        return string.substring(0, 2);
    }

    static public String midTwo(String string) {
        return string.substring(3, 5);
    }

    static String lastFour(String string) {
        return string.substring(6);
    }

    static public int getMonth() {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MM", Locale.UK);
        String stringFormat = format1.format(rightNow.getTime());
        return Integer.parseInt(stringFormat);
    }

    static public Calendar getToday() {
        Calendar rightNow = Calendar.getInstance();
        rightNow.getTime();
        return rightNow;
    }

    static public Calendar getCalendarFromString(String string) {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy", Locale.UK);
        try {
            rightNow.setTime(format1.parse(string));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rightNow;
    }

    static public String normalDate(String string) {

        char[] c = string.toCharArray();

        // Replace with a "swap" function, if desired:
        char temp = c[0];
        c[0] = c[3];
        c[3] = temp;
        char temp1 = c[1];
        c[1] = c[4];
        c[4] = temp1;
        return new String(c);
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
