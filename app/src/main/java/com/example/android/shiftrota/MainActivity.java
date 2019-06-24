package com.example.android.shiftrota;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
//import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements DateAdapter.AdapterCallback {

    static final int NUMBER_OF_COLUMNS = 7;
    static String originalString1, originalString2, originalString3;
    //    String selection;
    DateAdapter dateAdapter;
    RelativeLayout hiddenLayout;
    TextView titleTextView, monthTextView;
    MaterialButton cancelImageView, willWorkImageView,
            haveWorkedImageView, holidayImageView;
    //    Spinner statusSpinner;
    TextInputEditText hoursEditText, notesEditText;
    //    Button  monthIncrementButton, monthDecrementButton;
    ImageView saveImageView, clearImageView;
    int statusInt;
    int originalStatusInt;
    Date date;
    int klein = DatesGenerator.getMonth();
    DateViewModel mDateViewModel;
    private GestureDetectorCompat gestureDetector;
    Calendar calendar;

    public void onMethodCallback(String yourValue1, String yourValue2, String yourValue3, int gStatusInt) {
//        DatesGenerator.layoutAnimation(hiddenLayout, true);
//        hiddenLayout.setElevation(4);
        originalString1 = yourValue1;
        originalString2 = yourValue2;
        originalString3 = yourValue3;

        if (originalString2.matches("0")) {
            Objects.requireNonNull(hoursEditText.getText()).clear();
        } else {
            hoursEditText.setText(originalString2);
        }

        notesEditText.setText(originalString3);
        originalStatusInt = gStatusInt;
        titleTextView.setText(DatesGenerator.normalDate(yourValue1));
//        titleTextView.setElevation(12);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureDetector = new GestureDetectorCompat(this, new MainActivity.LearnGesture());

        Window window = MainActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimaryDark));

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        hiddenLayout = findViewById(R.id.hidden_linear_layout);
        titleTextView = findViewById(R.id.title_text_view);
        monthTextView = findViewById(R.id.month_text_view);
        cancelImageView = findViewById(R.id.cancel_text_view);
        willWorkImageView = findViewById(R.id.will_work_text_view);
        haveWorkedImageView = findViewById(R.id.have_worked_text_view);
        holidayImageView = findViewById(R.id.holiday_text_view);
//        statusSpinner = findViewById(R.id.status_spinner);
        hoursEditText = findViewById(R.id.hours_edit_text);
        notesEditText = findViewById(R.id.notes_edit_text);
        saveImageView = findViewById(R.id.save_image_view);
        clearImageView = findViewById(R.id.clear_image_view);
//        monthDecrementButton = findViewById(R.id.month_decrement);
//        monthIncrementButton = findViewById(R.id.month_increment);

        Observer<List<Date>> aNewDate = date -> dateAdapter.setDates(date);

        mDateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);
        mDateViewModel.getAnotherMonth().observe(this, aNewDate);
        mDateViewModel.setInput(klein);

        monthTextView.setText(DatesGenerator.nameOfMonth(klein));

        titleTextView.setText(DatesGenerator.today());

        GridLayoutManager gridLayout = new GridLayoutManager(this, NUMBER_OF_COLUMNS);
        recyclerView.setLayoutManager(gridLayout);
        int etsi = gridLayout.getSpanCount();
        Log.d("MainActivity", "span count" + etsi);

        dateAdapter = new DateAdapter(this, this);
        recyclerView.setAdapter(dateAdapter);

        cancelImageView.setOnClickListener(view -> statusInt = 0);

        willWorkImageView.setOnClickListener(view -> statusInt = 1);

        haveWorkedImageView.setOnClickListener(view -> statusInt = 2);

        holidayImageView.setOnClickListener(view -> statusInt = 3);

//        monthDecrementButton.setOnClickListener(v -> {
//            if (klein == 1) {
//                return;
//            }
//            klein--;
//            mDateViewModel.setInput(klein);
//            monthTextView.setText(DatesGenerator.nameOfMonth(klein));
//        });

//        monthIncrementButton.setOnClickListener(v -> {
//            if (klein == 12) {
//                return;
//            }
//            klein++;
//            mDateViewModel.setInput(klein);
//            monthTextView.setText(DatesGenerator.nameOfMonth(klein));
//        });

        saveImageView.setOnClickListener(v -> {
            String hoursString = Objects.requireNonNull(hoursEditText.getText()).toString().trim();
            String notesString = Objects.requireNonNull(notesEditText.getText()).toString().trim();

            if (statusInt == 1 && DatesGenerator.getCalendarFromString(originalString1).
                    before(DatesGenerator.getToday())) {
                Toast.makeText(MainActivity.this, getResources().
                                getString(R.string.cant_book_in_the_past),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, getResources().
                                getString(R.string.change_status_or_date_in_future),
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (statusInt == 2 && DatesGenerator.getCalendarFromString(originalString1).
                    after(DatesGenerator.getToday())) {
                Toast.makeText(MainActivity.this, getResources().
                                getString(R.string.cant_book_in_the_future),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, getResources().
                                getString(R.string.change_status_or_date_in_past),
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (statusInt == 0 || statusInt == 3) {
                date = new Date(originalString1, statusInt, null, notesString);
                mDateViewModel.insert(date);
            }
            if ((statusInt == 1 || statusInt == 2) &&
                    (hoursString.isEmpty() || hoursString.matches("0") ||
                            hoursString.matches("00"))) {
                Toast.makeText(MainActivity.this, "Please give the amount of hours",
                        Toast.LENGTH_SHORT).show();
                return;
            } else {
                date = new Date(originalString1, statusInt, hoursString, notesString);
                mDateViewModel.insert(date);
            }
//            DatesGenerator.layoutAnimation(hiddenLayout, false);
        });

//        clearImageView.setOnClickListener(v ->
//                DatesGenerator.layoutAnimation(hiddenLayout, false));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("klein", klein);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        klein = savedInstanceState.getInt("klein");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDateViewModel.setInput(klein);
        monthTextView.setText(DatesGenerator.nameOfMonth(klein));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDateViewModel.setInput(klein);
        monthTextView.setText(DatesGenerator.nameOfMonth(klein));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                if (klein == 1) {
                    return false;
                } else {
                    klein--;
                    mDateViewModel.setInput(klein);
                    monthTextView.setText(DatesGenerator.nameOfMonth(klein));
                }
            }
            if (event2.getX() < event1.getX()) {
                if (klein == 12) {
                    return false;
                } else {
                    klein++;
                    mDateViewModel.setInput(klein);
                    monthTextView.setText(DatesGenerator.nameOfMonth(klein));
                }
            }
            return true;
        }
    }

//    private void setUpSpinner() {
//
//        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
//                R.array.array_hours_options, android.R.layout.simple_spinner_item);
//
//        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//        statusSpinner.setAdapter(statusSpinnerAdapter);
//
//        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selection = (String) parent.getItemAtPosition(position);
//                if (!selection.isEmpty()) {
//                    if (selection.equals(getString(R.string.cancel))) {
//                        statusInt = 0;
//                    } else if (selection.equals(getString(R.string.will_work))) {
//                        statusInt = 1;
//                    } else if (selection.equals(getString(R.string.have_worked))) {
//                        statusInt = 2;
//                    } else if (selection.equals(getString(R.string.holiday))) {
//                        statusInt = 3;
//                    }
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                statusInt = 0;
//            }
//        });
//    }
}
