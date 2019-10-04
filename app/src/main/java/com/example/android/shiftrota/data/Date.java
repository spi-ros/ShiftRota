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
    }

    @NonNull
    public String getDate() {
        return mDate;
    }

    void setDate(String date) {
        this.mDate = date;
        this.mStatus = 0;
    }

    void setStatus (int status) {
        this.mStatus = status;
    }
    public int getStatus() {
        return mStatus;
    }

    public String getHours() { return  mHours;}

    public String getNotes() {
        return mNotes;
    }
}
