package com.example.android.shiftrota;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.shiftrota.DateAdapter;
import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DateAdapter dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        DateViewModel mDateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        mDateViewModel.getAllDates().observe(this, new Observer<List<Date>>() {
            @Override
            public void onChanged(@Nullable List<Date> dates) {
                dateAdapter.setDates(dates);
            }
        });

        int numberOfColumns = 7;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        dateAdapter = new DateAdapter(this);

        recyclerView.setAdapter(dateAdapter);

//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "You clicked " + ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
//
//                ((TextView) view).setTextColor(Color.BLUE);
//            }
//        });
    }
}
