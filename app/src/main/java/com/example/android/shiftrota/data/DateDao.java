package com.example.android.shiftrota.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DateDao {

    @Insert
    void insert (Date date);

    @Query("DELETE FROM date_table")
    void deleteAll();

    @Query("SELECT * FROM date_table ORDER BY date ASC")
    List<Date> getAllDates();
}
