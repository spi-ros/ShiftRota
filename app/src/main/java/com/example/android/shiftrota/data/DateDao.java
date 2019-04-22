package com.example.android.shiftrota.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DateDao {

    @Query("SELECT * from date_table LIMIT 1")
    Date[] getAnyWord();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Date date);

    @Query("DELETE FROM date_table")
    void deleteAll();

    @Query("SELECT * FROM date_table ORDER BY date ASC")
    LiveData<List<Date>> getAllDates();

    @Query("SELECT * FROM date_table WHERE date BETWEEN :search AND :search1")
    LiveData<List<Date>> loadByMonth(String search, String search1);
}
