package com.example.android.shiftrota;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.example.android.shiftrota.databinding.MainConstraintBinding;
import com.example.android.shiftrota.selection.DetailsLookup;
import com.example.android.shiftrota.selection.KeyProvider;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    static final int NUMBER_OF_COLUMNS = 7;
    int monthInt = DatesGenerator.getMonthInt();
    DateAdapter dateAdapter;
    SelectionTracker<Long> tracker;
    DateViewModel mDateViewModel;
    int statusChangedValue;
    int hoursChangedValue;
    BottomSheetBehavior bottomSheetBehavior;
    GestureDetectorCompat gestureDetector;
    private Calendar rightNow = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
    private String formattedString = simpleDateFormat.format(rightNow.getTime());
    private int year = Integer.parseInt(DatesGenerator.firstFour(formattedString));
    private MainConstraintBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainConstraintBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        assert binding.collapsingRelativeLayout != null;
        bottomSheetBehavior = BottomSheetBehavior.from(binding.collapsingRelativeLayout);

        String[] data = {"Clear", "Work", "Worked", "Holiday"};
        assert binding.statusPicker != null;
        binding.statusPicker.setMinValue(1);
        binding.statusPicker.setMaxValue(data.length);
        binding.statusPicker.setDisplayedValues(data);
        binding.statusPicker.setDividerDistance(200);
        binding.statusPicker.setDividerThickness(3);
        binding.statusPicker.setOnValueChangedListener((picker, oldVal, newVal) ->
                statusChangedValue = newVal);

//        String[] pickerHoursString = {"1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5",
//        "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10", "10.5", "11",
//        "11.5", "12", "12.5", "13", "13.5", "14", "14.5", "15"};

        assert binding.hoursNumberPicker != null;
        binding.hoursNumberPicker.setMinValue(1);
        binding.hoursNumberPicker.setMaxValue(15);
//        hoursNumberPicker.setDisplayedValues(pickerHoursString);
        binding.hoursNumberPicker.setDividerThickness(3);
        binding.hoursNumberPicker.setOnValueChangedListener(((picker, oldVal, newVal) ->
                hoursChangedValue = newVal));

        gestureDetector = new GestureDetectorCompat(this, new LearnGesture());

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
            binding.hoursWorkedNumberTextView.setText(String.format(getResources().
                    getString(R.string.hours_worked_sum), stringDouble));
            String stringDouble1 = Double.toString(bookedStatusDouble1);
            binding.hoursBookedNumberTextView.setText(String.format(getResources().
                    getString(R.string.hours_booked_sum), stringDouble1));
        };

        ViewModelProvider modelProvider = new ViewModelProvider(this);
        mDateViewModel = modelProvider.get(DateViewModel.class);

        mDateViewModel.getAnotherMonth().observe(this, monthDates);
        mDateViewModel.setInputMonth(monthInt);

        mDateViewModel.getWorkedHours().observe(this, hoursObserver);
        mDateViewModel.setInputWorkedHours(monthInt);

        binding.monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));

        GridLayoutManager gridLayout = new GridLayoutManager(this, NUMBER_OF_COLUMNS);
        binding.recyclerView.setLayoutManager(gridLayout);

        dateAdapter = new DateAdapter(this);
        binding.recyclerView.setAdapter(dateAdapter);

        tracker = new SelectionTracker.Builder<>(
                "selection",
                binding.recyclerView,
                new KeyProvider(dateAdapter),
                new DetailsLookup(binding.recyclerView),
                StorageStrategy.createLongStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectAnything())
                .build();

        dateAdapter.setSelectionTracker(tracker);

        tracker.addObserver(
                new SelectionTracker.SelectionObserver<Long>() {
                    @Override
                    public void onSelectionChanged() {
                        if (tracker.getSelection().size() > 0) {

                            binding.notesEditText.setText("");

                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            int selectionSizeInt = tracker.getSelection().size();

                            String trackerSelectionString = tracker.getSelection().toString();
                            String search = trackerSelectionString.
                                    substring(trackerSelectionString.indexOf("[") + 1,
                                            trackerSelectionString.indexOf("]"));
                            List<String> searchItems = Arrays.asList(search.split(", "));
                            List<String> datesList = new ArrayList<>();

                            for (int i = 0; i < searchItems.size(); i++) {
                                String dateString = dateAdapter.
                                        getItemInPosition(Integer.parseInt(searchItems.get(i)));
                                int monthCheckInt = Integer.parseInt(DatesGenerator.midTwo(dateString));
                                if (monthCheckInt != monthInt) {
                                    if (tracker.isSelected(Long.parseLong(searchItems.get(i)))) {
                                        selectionSizeInt = selectionSizeInt - 1;
                                    }
                                } else {
                                    datesList.add(dateString);
                                }
                            }

                            String selectionSizeString = String.valueOf(selectionSizeInt);
                            if (selectionSizeInt == 0) {
                                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            }
                            assert binding.selectedTextView != null;
                            binding.selectedTextView.setText(selectionSizeString);

                            if (selectionSizeInt == 1) {
                                String notesString = dateAdapter.
                                        getItemNotesInPosition(Integer.parseInt(searchItems.get(0)));
                                binding.notesEditText.setText(notesString);

                                Configuration config = getResources().getConfiguration();
                                int screenHeight = config.screenHeightDp;

                                if (screenHeight < 700) {
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                } else {
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                }
                            }
                            binding.saveButton.setOnClickListener(view -> DatesGenerator.
                                    saveInit(statusChangedValue, hoursChangedValue,
                                            binding.notesEditText, datesList, mDateViewModel, tracker,
                                            getApplicationContext(), getResources()));
                        } else {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                            binding.statusPicker.setValue(1);
                            binding.hoursNumberPicker.setValue(1);
                        }
                    }

                    @Override
                    public void onItemStateChanged(@NonNull Long key, boolean selected) {
                        super.onItemStateChanged(key, selected);
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
        binding.monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDateViewModel.setInputMonth(monthInt);
        binding.monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
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
                binding.monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
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
                binding.monthTextView.setText(DatesGenerator.nameOfMonth(monthInt));
            }
            return true;
        }
    }
}
