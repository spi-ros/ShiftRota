package com.example.android.shiftrota;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.example.android.shiftrota.selection.DetailsLookup;
import com.example.android.shiftrota.selection.KeyProvider;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.shawnlin.numberpicker.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements DateAdapter.AdapterCallback {

    static final int NUMBER_OF_COLUMNS = 7;
    static String dateString;
    int statusInt;
    int monthInt = DatesGenerator.getMonthInt();
    Date date;
    DateAdapter dateAdapter;
    RecyclerView recyclerView;
    SelectionTracker<Long> tracker;
    TextView dateTextView, monthTextView, hoursWorkedNumberTextView,
            hoursBookedNumberTextView;
    MaterialButtonToggleGroup toggleGroup;
    MaterialButton cancelButton, willWorkButton,
            haveWorkedButton, holidayButton, saveButton;
    TextInputEditText notesEditText;
    DateViewModel mDateViewModel;
    private Calendar rightNow = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    private String formattedString = simpleDateFormat.format(rightNow.getTime());
    private int year = Integer.parseInt(DatesGenerator.firstFour(formattedString));
    private GestureDetectorCompat gestureDetector;
    NumberPicker statusNumberPicker, hoursNumberPicker;
    int statusChangedValue, hoursChangedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_constraint);

        recyclerView = findViewById(R.id.recycler_view);
        dateTextView = findViewById(R.id.date_text_view);
        monthTextView = findViewById(R.id.month_text_view);
        toggleGroup = findViewById(R.id.toggle_button_group);
        cancelButton = findViewById(R.id.cancel_button);
        willWorkButton = findViewById(R.id.will_work_button);
        haveWorkedButton = findViewById(R.id.have_worked_button);
        holidayButton = findViewById(R.id.holiday_button);
//        hoursEditText = findViewById(R.id.hours_edit_text);
        notesEditText = findViewById(R.id.notes_edit_text);
        saveButton = findViewById(R.id.save_button);
        hoursWorkedNumberTextView = findViewById(R.id.hours_worked_number_text_view);
        hoursBookedNumberTextView = findViewById(R.id.hours_booked_number_text_view);
        statusNumberPicker = findViewById(R.id.status_number_picker);
        hoursNumberPicker = findViewById(R.id.hours_number_picker);

        String[] data = {"Clear", "Work", "Worked", "Holiday"};
        statusNumberPicker.setMinValue(1);
        statusNumberPicker.setMaxValue(data.length);
        statusNumberPicker.setDisplayedValues(data);
        statusNumberPicker.setDividerDistance(200);
        statusNumberPicker.setDividerThickness(3);
        statusNumberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> {
            statusChangedValue = newVal;
        });

        hoursNumberPicker.setMinValue(1);
        hoursNumberPicker.setMaxValue(15);
        hoursNumberPicker.setDividerThickness(3);
        hoursNumberPicker.setOnValueChangedListener(((picker, oldVal, newVal) -> {
            hoursChangedValue = newVal;
        }));

        gestureDetector = new GestureDetectorCompat(this, new MainActivity.LearnGesture());

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        Window window = MainActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

        Observer<List<Date>> monthDates = dates -> {
            for (int i = 0; i < dates.size(); i++) {
                Date date = dates.get(i);
                int status = date.getStatus();
                String dateString = date.getDate();
                if (status == 1 && (dateString.compareTo(DatesGenerator.todayTheOtherWay()) == 0 ||
                        dateString.compareTo(DatesGenerator.todayTheOtherWay()) < 0)) {
                    Date updateDate = new Date(dateString,
                            2, date.getHours(), date.getNotes());

                    mDateViewModel.insert(updateDate);
                }
            }
            dateAdapter.setDates(dates, monthInt);
        };

        Observer<List<Date>> hoursObserver = dates -> {
            double bookedStatusDouble;
            double bookedStatusDouble1 = 0.0;
            double workedStatusDouble;
            double workedStatusDouble1 = 0.0;
            for (int i = 0; i < dates.size(); i++) {
                Date date = dates.get(i);
                int status = date.getStatus();
                if (status == 2) {
                    workedStatusDouble = Double.parseDouble(date.getHours());
                    workedStatusDouble1 = workedStatusDouble1 + workedStatusDouble;
                }

                if (status == 1) {
                    bookedStatusDouble = Double.parseDouble(date.getHours());
                    bookedStatusDouble1 = bookedStatusDouble1 + bookedStatusDouble;
                }
            }
            String stringDouble = Double.toString(workedStatusDouble1);
            hoursWorkedNumberTextView.setText(String.format(getResources().
                    getString(R.string.hours_worked_sum), stringDouble));
            String stringDouble1 = Double.toString(bookedStatusDouble1);
            hoursBookedNumberTextView.setText(String.format(getResources().
                    getString(R.string.hours_booked_sum), stringDouble1));
        };

        ViewModelProvider modelProvider = new ViewModelProvider(this);
        mDateViewModel = modelProvider.get(DateViewModel.class);

        mDateViewModel.getAnotherMonth().observe(this, monthDates);
        mDateViewModel.setInputMonth(monthInt);

        mDateViewModel.getWorkedHours().observe(this, hoursObserver);
        mDateViewModel.setInputWorkedHours(monthInt);

        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
        dateTextView.setText(DatesGenerator.today());
        if (dateString == null) {
            dateString = DatesGenerator.today();
        }

        GridLayoutManager gridLayout = new GridLayoutManager(this, NUMBER_OF_COLUMNS);
        recyclerView.setLayoutManager(gridLayout);

        dateAdapter = new DateAdapter(this);
        recyclerView.setAdapter(dateAdapter);

        tracker = new SelectionTracker.Builder<>(
                "selection",
                recyclerView,
                new KeyProvider(dateAdapter),
                new DetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectAnything())
                .build();

        dateAdapter.setSelectionTracker(tracker);
        statusNumberPicker.setOnClickListener(view ->
                Log.d("MainActivity", "Number Picker OnClick " + statusNumberPicker.getValue()));

//        cancelButton.setOnClickListener(view -> statusInt = 0);
//
//        willWorkButton.setOnClickListener(view -> statusInt = 1);
//
//        haveWorkedButton.setOnClickListener(view -> statusInt = 2);
//
//        holidayButton.setOnClickListener(view -> statusInt = 3);

        tracker.addObserver(
                new SelectionTracker.SelectionObserver<Long>() {
                    @Override
                    public void onSelectionChanged() {
                        if (tracker.getSelection().size() > 0) {
                            String testString01 = tracker.getSelection().toString();
                            String search = testString01.substring(testString01.indexOf("[") + 1, testString01.indexOf("]"));
                            List<String> searchItems = Arrays.asList(search.split(", "));
                            List<String> testList = new ArrayList<>();

                            Log.d("MainActivity", "testString01 " + testString01);

                            for (int i = 0; i < searchItems.size(); i++) {
                                String klein = dateAdapter.getItemInPosition(Integer.parseInt(searchItems.get(i)));
                                testList.add(klein);
                            }
                            Log.d("MainActivity", "searchItems " + searchItems);
                            Log.d("MainActivity", "testList " + testList);

                            saveButton.setOnClickListener(view -> {
                                statusInt = statusChangedValue - 1;
//                                Log.d("MainActivity", "klein " + klein);
                                String hoursString = String.valueOf(hoursChangedValue);
                                String notesString = Objects.requireNonNull(notesEditText.getText()).toString().trim();
                                for (int i = 0; i < testList.size(); i++) {
                                    if (statusInt == 1 && DatesGenerator.getCalendarFromString(testList.get(i)).
                                            before(DatesGenerator.getToday())) {
                                        Toast.makeText(MainActivity.this, getResources().
                                                        getString(R.string.cant_book_in_the_past),
                                                Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, getResources().
                                                        getString(R.string.change_status_or_date_in_future),
                                                Toast.LENGTH_SHORT).show();
                                    } else if (statusInt == 2 && DatesGenerator.getCalendarFromString(testList.get(i)).
                                            after(DatesGenerator.getToday())) {
                                        Toast.makeText(MainActivity.this, getResources().
                                                        getString(R.string.cant_book_in_the_future),
                                                Toast.LENGTH_SHORT).show();
                                        Toast.makeText(MainActivity.this, getResources().
                                                        getString(R.string.change_status_or_date_in_past),
                                                Toast.LENGTH_SHORT).show();
                                    } else if ((statusInt == 1 || statusInt == 2) &&
                                            (hoursString.isEmpty() || hoursString.matches("0") ||
                                                    hoursString.matches("00"))) {
                                        Toast.makeText(MainActivity.this, "Please give the amount of hours",
                                                Toast.LENGTH_SHORT).show();
                                    } else if (statusInt == 0) {
                                        for (int j = 0; j < testList.size(); j++) {
                                            date = new Date(testList.get(j), statusInt, null, null);
                                            mDateViewModel.insert(date);
                                        }
                                    } else if (statusInt == 3) {
                                        for (int j = 0; j < testList.size(); j++) {
                                            date = new Date(testList.get(j), statusInt, null, notesString);
                                            mDateViewModel.insert(date);
                                        }
                                    } else {
                                        for (int j = 0; j < testList.size(); j++) {
                                            date = new Date(testList.get(j), statusInt, hoursString, notesString);
                                            mDateViewModel.insert(date);
                                        }
                                    }
                                }
                                if (tracker.hasSelection()) {
                                    tracker.clearSelection();
                                }
                            });
                        }
                    }
                });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("monthInt", monthInt);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        monthInt = savedInstanceState.getInt("monthInt");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDateViewModel.setInputMonth(monthInt);
        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDateViewModel.setInputMonth(monthInt);
        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
    }

    @Override
    public void onBackPressed() {
        if (tracker.hasSelection()) {
            tracker.clearSelection();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    /**
     * This method hides the keyboard if the user touches anywhere else on the screen
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP ||
                ev.getAction() == MotionEvent.ACTION_MOVE) &&
                view instanceof EditText &&
                !view.getClass().getName().startsWith("android.webkit.")) {
            int[] screen = new int[2];
            view.getLocationOnScreen(screen);
            float x = ev.getRawX() + view.getLeft() - screen[0];
            float y = ev.getRawY() + view.getTop() - screen[1];
            if (x < view.getLeft() || x > view.getRight() ||
                    y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) Objects.requireNonNull(this.
                        getSystemService(Context.INPUT_METHOD_SERVICE))).
                        hideSoftInputFromWindow((this.getWindow().
                                getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void onMethodCallback(String dateString, String hoursString, String notesString, int originalStatusInt) {

        MainActivity.dateString = dateString;
        statusInt = originalStatusInt;

//        if (hoursString == null || hoursString.matches("null")) {
//            Objects.requireNonNull(hoursEditText.getText()).clear();
//        } else {
//            hoursEditText.setText(hoursString);
//        }

        notesEditText.setText(notesString);

        // get the center for the clipping circle
        int cx = dateTextView.getWidth() / 2;
        int cy = dateTextView.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(dateTextView, cx, cy, 0f, finalRadius);
        anim.setDuration(550);

        // make the view visible and start the animation
        dateTextView.setVisibility(View.VISIBLE);
        anim.start();
        dateTextView.setText(DatesGenerator.normalDate(dateString));

        toggleGroup.check(R.id.toggle_button_group);
        switch (statusInt) {
            case 0:
                toggleGroup.clearChecked();
                statusNumberPicker.setValue(1);
                break;
            case 1:
                toggleGroup.check(R.id.will_work_button);
                statusNumberPicker.setValue(2);
                break;
            case 2:
                toggleGroup.check(R.id.have_worked_button);
                statusNumberPicker.setValue(3);
                break;
            case 3:
                toggleGroup.check(R.id.holiday_button);
                statusNumberPicker.setValue(4);
                break;
        }
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (event2.getX() > event1.getX()) {
                if (monthInt == 1) {
                    monthInt = 13;
                    year = year - 1;
                    mDateViewModel.setYears(year);
                }

                if (tracker.hasSelection()) {
                    tracker.clearSelection();
                }

                monthInt--;
                mDateViewModel.setInputMonth(monthInt);
                mDateViewModel.setInputWorkedHours(monthInt);
                monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
            }
            if (event2.getX() < event1.getX()) {
                if (monthInt == 12) {
                    monthInt = 0;
                    year = year + 1;
                    mDateViewModel.setYears(year);
                }

                if (tracker.hasSelection()) {
                    tracker.clearSelection();
                }

                monthInt++;
                mDateViewModel.setInputMonth(monthInt);
                mDateViewModel.setInputWorkedHours(monthInt);
                monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
            }
            return true;
        }
    }
}
