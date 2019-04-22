package com.example.android.shiftrota.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "date_table", indices = @Index(value = {"date"}, unique = true))
public class Date {


//    @NonNull
//    @ColumnInfo(name = "monthDayYear")
//    private String monthDayYear;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "status")
    private int mStatus;

    @ColumnInfo (name = "hours")
    private String mHours;

    @ColumnInfo (name = "notes")
    private String mNotes;

    public Date(@NonNull String date, int status, String hours, String notes) {
        this.mDate = date;
        this.mStatus = status;
        this.mHours = hours;
        this.mNotes = notes;
//        this.monthDayYear = monthDayYear;
    }

    @NonNull
    public String getDate() {
        return mDate;
    }

    void setDate(String date) {
        this.mDate = date;
        this.mStatus = 0;
    }

    public int getStatus() {
        return mStatus;
    }

    public String getHours() { return  mHours;}

    String getNotes() {
        return mNotes;
    }


//    @NonNull
//    public String getMonthDayYear() {
//        return monthDayYear;
//    }
}
