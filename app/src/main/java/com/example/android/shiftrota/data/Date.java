package com.example.android.shiftrota.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "date_table", indices = @Index(value = {"date"}, unique = true))
public class Date {

    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

    @ColumnInfo(name = "status")
    private int mStatus;

    public Date(@NonNull String date, int status) {
        this.mDate = date;
        this.mStatus = status;
    }

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
}
