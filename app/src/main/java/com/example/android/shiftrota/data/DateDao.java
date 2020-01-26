package com.example.android.shiftrota.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DateDao {

    @Query("SELECT * from date_table LIMIT 1")
    Date[] getAnyWord();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Date date);

    @Query("DELETE FROM date_table")
    void deleteAll();

//    @Query("SELECT * FROM date_table ORDER BY date ASC")
//    LiveData<List<Date>> getAllDates();

    @Query("SELECT * FROM date_table WHERE date BETWEEN :search AND :search1")
    LiveData<List<Date>> loadByMonth(String search, String search1);

//    @Query("SELECT hours FROM date_table WHERE date BETWEEN :search AND :search1")
//    LiveData<List<String>> loadHoursByMonth(String search, String search1);

//    **@Query("SELECT status FROM date_table WHERE status = 1  BEFORE :search")
//    LiveData<List<Date>> statusUpdate(int search);

//    @Query("UPDATE date_table SET status = 2 WHERE date BETWEEN :search AND :search1")
//    List<Date> statusUpdate(int search, int search1);

}
