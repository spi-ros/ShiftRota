package com.example.android.shiftrota.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromDate(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
        // return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static String arraylistToString(ArrayList<String> list) {
        Gson gson = new Gson();

        return gson.toJson(list);
        // return date == null ? null : date.getTime();
    }
}