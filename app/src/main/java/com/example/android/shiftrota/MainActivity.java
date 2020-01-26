package com.example.android.shiftrota;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Context;
import android.os.Bundle;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StableIdKeyProvider;
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
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.example.android.shiftrota.selection.MyItemDetailsLookup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity implements DateAdapter.AdapterCallback {

    int statusInt;
    int monthInt = DatesGenerator.getMonthInt();
    Date date;
    DateAdapter dateAdapter;
    SelectionTracker<Long> tracker;
    static final int NUMBER_OF_COLUMNS = 7;
    static String dateString;
    TextView dateTextView, monthTextView, hoursWorkedNumberTextView,
            hoursBookedNumberTextView;
    MaterialButtonToggleGroup toggleGroup;
    MaterialButton cancelButton, willWorkButton,
            haveWorkedButton, holidayButton, saveButton;
    TextInputEditText hoursEditText, notesEditText;
//    NumberPicker numberPicker;
    DateViewModel mDateViewModel;
    private Calendar rightNow = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    private String formattedString = simpleDateFormat.format(rightNow.getTime());
    private int year = Integer.parseInt(DatesGenerator.firstFour(formattedString));
    private GestureDetectorCompat gestureDetector;
//    RelativeLayout optionsLayout;
    //    Button  monthIncrementButton, monthDecrementButton;
    //    Spinner statusSpinner;

    public void onMethodCallback(String dateString, String hoursString, String notesString, int originalStatusInt) {
//        DatesGenerator.layoutAnimation(hiddenLayout, true);
//        hiddenLayout.setElevation(4);

        MainActivity.dateString = dateString;
        statusInt = originalStatusInt;

        if (hoursString == null || hoursString.matches("null")) {
            Objects.requireNonNull(hoursEditText.getText()).clear();
        } else {
            hoursEditText.setText(hoursString);
        }

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
                break;
            case 1:
                toggleGroup.check(R.id.will_work_button);
                break;
            case 2:
                toggleGroup.check(R.id.have_worked_button);
                break;
            case 3:
                toggleGroup.check(R.id.holiday_button);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_constraint);

        int testInt = monthInt;

        gestureDetector = new GestureDetectorCompat(this, new MainActivity.LearnGesture());


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Log.d("MAINACTIVITY", "DISPLAY METRICS " + displayMetrics);

        Window window = MainActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        dateTextView = findViewById(R.id.date_text_view);
        monthTextView = findViewById(R.id.month_text_view);
        toggleGroup = findViewById(R.id.toggle_button_group);
        cancelButton = findViewById(R.id.cancel_button);
        willWorkButton = findViewById(R.id.will_work_button);
        haveWorkedButton = findViewById(R.id.have_worked_button);
        holidayButton = findViewById(R.id.holiday_button);
        hoursEditText = findViewById(R.id.hours_edit_text);
        notesEditText = findViewById(R.id.notes_edit_text);
        saveButton = findViewById(R.id.save_button);
        hoursWorkedNumberTextView = findViewById(R.id.hours_worked_number_text_view);
        hoursBookedNumberTextView = findViewById(R.id.hours_booked_number_text_view);


//        numberPicker = findViewById(R.id.number_picker);
//        statusSpinner = findViewById(R.id.status_spinner);
//        optionsLayout = findViewById(R.id.options_relative_layout);

//        numberPicker.setMinValue(1);
//
//        numberPicker.setMaxValue(15);
//        numberPicker.setWrapSelectorWheel(true);
//        numberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
//
//        });

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

        mDateViewModel = ViewModelProviders.of(this).get(DateViewModel.class);

        mDateViewModel.getAnotherMonth().observe(this, monthDates);
        mDateViewModel.setInputMonth(monthInt);

        mDateViewModel.getWorkedHours().observe(this, hoursObserver);
        mDateViewModel.setInputWorkedHours(monthInt);

        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
        dateTextView.setText(DatesGenerator.today());

        GridLayoutManager gridLayout = new GridLayoutManager(this, NUMBER_OF_COLUMNS);
        recyclerView.setLayoutManager(gridLayout);

        dateAdapter = new DateAdapter(this);

        dateAdapter.setHasStableIds(true);

        recyclerView.setAdapter(dateAdapter);

        tracker = new SelectionTracker.Builder<Long>(
                "my-selection-id",
                recyclerView,
                new StableIdKeyProvider(recyclerView),
                new MyItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectAnything())
                .build();

        tracker = dateAdapter.tracker;

        cancelButton.setOnClickListener(view -> statusInt = 0);

        willWorkButton.setOnClickListener(view -> statusInt = 1);

        haveWorkedButton.setOnClickListener(view -> statusInt = 2);

        holidayButton.setOnClickListener(view -> statusInt = 3);

//        monthDecrementButton.setOnClickListener(v -> {
//            if (monthInt == 1) {
//                return;
//            }
//            monthInt--;
//            mDateViewModel.setInputMonth(monthInt);
//            monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
//        });

//        monthIncrementButton.setOnClickListener(v -> {
//            if (monthInt == 12) {
//                return;
//            }
//            monthInt++;
//            mDateViewModel.setInputMonth(monthInt);
//            monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
//        });

//        MaterialButtonToggleGroup.OnButtonCheckedListener listener = (group, checkedId, isChecked) -> {
//            if (!isChecked)
//                Toast.makeText(MainActivity.this, "Testing", Toast.LENGTH_SHORT).show();
//        };

        saveButton.setOnClickListener(v -> {
//            toggleGroup.addOnButtonCheckedListener(listener);
            String hoursString = Objects.requireNonNull(hoursEditText.getText()).toString().trim();
            String notesString = Objects.requireNonNull(notesEditText.getText()).toString().trim();

            if (dateString == null)
                dateString = DatesGenerator.today();

            if (statusInt == 1 && DatesGenerator.getCalendarFromString(dateString).
                    before(DatesGenerator.getToday())) {
                Toast.makeText(MainActivity.this, getResources().
                                getString(R.string.cant_book_in_the_past),
                        Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, getResources().
                                getString(R.string.change_status_or_date_in_future),
                        Toast.LENGTH_SHORT).show();
            } else if (statusInt == 2 && DatesGenerator.getCalendarFromString(dateString).
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
                date = new Date(dateString, statusInt, null, null);
                mDateViewModel.insert(date);
            } else if (statusInt == 3) {
                date = new Date(dateString, statusInt, null, notesString);
                mDateViewModel.insert(date);
            } else {
                date = new Date(dateString, statusInt, hoursString, notesString);
                mDateViewModel.insert(date);
            }
            Log.d("MAINACTIVITY", "STATUSINT " + statusInt);
//            DatesGenerator.layoutAnimation(hiddenLayout, false);
        });

//        clearImageView.setOnClickListener(v ->
//                DatesGenerator.layoutAnimation(hiddenLayout, false));
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
        super.onBackPressed();
        mDateViewModel.setInputMonth(monthInt);
        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
        Log.d("BACK", "BACK");
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
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] screen = new int[2];
            view.getLocationOnScreen(screen);
            float x = ev.getRawX() + view.getLeft() - screen[0];
            float y = ev.getRawY() + view.getTop() - screen[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) Objects.requireNonNull(this.getSystemService(Context.INPUT_METHOD_SERVICE))).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
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
                monthInt++;
                mDateViewModel.setInputMonth(monthInt);
                mDateViewModel.setInputWorkedHours(monthInt);
                monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
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
