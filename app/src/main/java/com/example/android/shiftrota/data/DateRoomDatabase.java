package com.example.android.shiftrota.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Date.class}, version = 1)
public abstract class DateRoomDatabase extends RoomDatabase {

    public abstract DateDao dateDao();

    /*Make the DateRoomDatabase a singleton to prevent having multiple instances of the database
     opened at the same time */
    private static volatile DateRoomDatabase INSTANCE;

    static DateRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DateRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DateRoomDatabase.class, "date_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
