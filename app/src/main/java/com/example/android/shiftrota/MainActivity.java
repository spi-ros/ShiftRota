package com.example.android.shiftrota;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.data.DatesGenerator;

public class MainActivity extends AppCompatActivity {

    DateAdapter dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        int numberOfColumns = 7;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        dateAdapter = new DateAdapter(this, DatesGenerator.getDays());

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
