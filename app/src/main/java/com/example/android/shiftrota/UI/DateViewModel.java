package com.example.android.shiftrota.UI;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.annotation.NonNull;

import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DateRepository;

import java.util.List;

public class DateViewModel extends AndroidViewModel {

    private DateRepository mDateRepository;

    private MutableLiveData<Integer> searchByMonth = new MutableLiveData<>();

    private LiveData<List<Date>> anotherMonth =
            Transformations.switchMap(searchByMonth, (month) -> mDateRepository.getMonth(month));

    public DateViewModel(@NonNull Application application) {
        super(application);

        mDateRepository = new DateRepository(application);
    }

    public void setInput(int month) {
        searchByMonth.setValue(month);
    }

    public LiveData<List<Date>> getAnotherMonth() { return anotherMonth; }

    public void insert(Date date) {
        mDateRepository.insert(date);
    }
}
