package com.example.android.shiftrota.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;


@Entity(tableName = "date_table", indices = @Index(value = {"date"}, unique = true))
public class Date {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "status")
    private int status;

    @ColumnInfo(name = "startTime")
    private String startTime;

    @ColumnInfo(name = "endTime")
    private String endTime;

    @ColumnInfo(name = "hours")
    private String hours;

    @ColumnInfo(name = "lunchBreak")
    private String lunchBreak;

    @ColumnInfo(name = "notes")
    private String notes;

    public Date(@NonNull String date, int status, String startTime, String endTime, String hours, String lunchBreak, String notes) {
        this.date = date;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hours = hours;
        this.lunchBreak = lunchBreak;
        this.notes = notes;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public int getStatus() {
        return status;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getHours() {
        return hours;
    }

    public String getLunchBreak() { return lunchBreak; }

    public String getNotes() {
        return notes;
    }
}
