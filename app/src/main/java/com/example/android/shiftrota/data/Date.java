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
    private int id;

    @NonNull
    @ColumnInfo(name = "date")
    private ArrayList<String> mDate = new ArrayList<>();

//    @NonNull
//    @ColumnInfo(name = "status")
//    private String mStatus;

    public Date(@NonNull List<String> date) {
        this.mDate.addAll(date);
//        this.mStatus = status;
    }

    void setMDate(ArrayList<String> mDate) {
        this.mDate = mDate;
    }

    public ArrayList<String> getMDate() { return this.mDate; }

    void setId(int id) { this.id = id; }

    public int getId() { return this.id; }

//    public String getStatus() {return this.mStatus;}
}
