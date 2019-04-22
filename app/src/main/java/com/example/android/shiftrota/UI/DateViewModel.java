package com.example.android.shiftrota.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DateRepository;

import java.util.List;

public class DateViewModel extends AndroidViewModel {

    private DateRepository mDateRepository;

    private LiveData<List<Date>> mAllDates, nAllDates;

    public DateViewModel(@NonNull Application application) {
        super(application);
        mDateRepository = new DateRepository(application);
        mAllDates = mDateRepository.loadNextMonth();
        nAllDates = mDateRepository.loadByMonth();
    }

    public LiveData<List<Date>> getAllDates() {
        return mAllDates;
    }

    public LiveData<List<Date>> loadByMonth() {
        return nAllDates;
    }

    public LiveData<List<Date>> loadNextMonth() {
        return mAllDates;
    }



    public void insert(Date date) {
        mDateRepository.insert(date);
    }
}
