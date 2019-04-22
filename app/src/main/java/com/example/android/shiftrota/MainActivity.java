package com.example.android.shiftrota;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements DateAdapter.AdapterCallback {

    DateAdapter dateAdapter;

    LinearLayout hiddenLayout;
    TextView titleTextView;
    Spinner statusSpinner;
    EditText hoursEditText, notesEditText;
    Button saveButton, monthIncrement, monthDecrement;
    int statusInt;
    Date date;
    static String originalString;
    static String anotherString;



    public void onMethodCallback(String yourValue1) {
        // get the center for the clipping circle
        int cx = hiddenLayout.getWidth() / 2;
        int cy = hiddenLayout.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(hiddenLayout, cx, cy, 0f, finalRadius);
        anim.setDuration(550);

        // make the view visible and start the animation
        hiddenLayout.setVisibility(View.VISIBLE);
        anim.start();
        originalString = yourValue1;

        titleTextView.setText(normalDate());

    }

    static public String normalDate() {

        char[] c = originalString.toCharArray();

        // Replace with a "swap" function, if desired:
        char temp = c[0];
        c[0] = c[3];
        c[3] = temp;
        char temp1 = c[1];
        c[1] = c[4];
        c[4] = temp1;
        return new String(c);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);

        hiddenLayout = findViewById(R.id.hidden_linear_layout);
        hiddenLayout.setVisibility(View.INVISIBLE);

        titleTextView = findViewById(R.id.title_text_view);
        statusSpinner = findViewById(R.id.status_spinner);
        hoursEditText = findViewById(R.id.hours_edit_text);
        notesEditText = findViewById(R.id.notes_edit_text);
        saveButton = findViewById(R.id.save_button);
        monthDecrement = findViewById(R.id.month_decrement);
        monthIncrement = findViewById(R.id.month_increment);


        final SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
//                SimpleDateFormat format3 = new SimpleDateFormat("MM", Locale.ENGLISH);
        final Calendar rightNow = Calendar.getInstance();
        String stringFormat = format1.format(rightNow.getTime());
        final int year = Integer.parseInt(DatesGenerator.lastTwo(stringFormat));
        final int month = Integer.parseInt(DatesGenerator.firstTwo(stringFormat));

        final DateViewModel mDateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        mDateViewModel.loadByMonth().observe(this, new Observer<List<Date>>() {
            @Override
            public void onChanged(@Nullable List<Date> dates) {
                dateAdapter.setDates(dates);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoursString = hoursEditText.getText().toString().trim();
                String notesString = notesEditText.getText().toString().trim();

                if (statusInt == 0 || statusInt == 3) {
                    date = new Date(originalString, statusInt, null, notesString);
                    mDateViewModel.insert(date);
                }

                if ((statusInt == 1 || statusInt == 2) && hoursString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please give the amount of hours",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    date = new Date(originalString, statusInt, hoursString, notesString);
                    mDateViewModel.insert(date);
                }
                int cx = hiddenLayout.getWidth() / 2;
                int cy = hiddenLayout.getHeight() / 2;

                // get the initial radius for the clipping circle
                float initialRadius = (float) Math.hypot(cx, cy);

                // create the animation (the final radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(hiddenLayout, cx, cy, initialRadius, 0f);

                // make the view invisible when the animation is done
                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        hiddenLayout.setVisibility(View.INVISIBLE);
                    }
                });
                // start the animation
                anim.start();
            }
        });

        int numberOfColumns = 7;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        dateAdapter = new DateAdapter(this);

        recyclerView.setAdapter(dateAdapter);
        setUpSpinner();

        monthDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i=month; i>1; i--) {
                    mDateViewModel.loadNextMonth();
                }

//                DatesGenerator.firstTwo(originalString);
//                mDateViewModel.loadByMonth();
            }
        });
    }

    private void setUpSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter hoursSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_hours_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        hoursSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        statusSpinner.setAdapter(hoursSpinnerAdapter);

        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!selection.isEmpty()) {
                    if (selection.equals(getString(R.string.cancel))) {
                        statusInt = 0;
                    } else if (selection.equals(getString(R.string.will_work))) {
                        statusInt = 1;
                    }else if (selection.equals(getString(R.string.have_worked))) {
                        statusInt = 2;
                    } else if (selection.equals(getString(R.string.holiday))) {
                        statusInt = 3;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statusInt = 0;
            }
        });
    }
}
