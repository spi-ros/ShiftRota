package com.example.android.shiftrota.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;



@Entity(tableName = "date_table")
public class Date {

    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    @ColumnInfo(name = "date")
    private String mDate;

//    @NonNull
//    @ColumnInfo(name = "status")
//    private String mStatus;

    public Date(@NonNull String date) {
        this.mDate = date;
//        this.mStatus = status;
    }

    public String getDate() { return mDate; }

//    public void setDate(ArrayList<String> date) {
//        this.mDate = date;    }
//    public String getStatus() {return this.mStatus;}
}
