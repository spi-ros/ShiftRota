package com.example.android.shiftrota;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.text.ParseException;
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
    String hoursChangedValue;
    BottomSheetBehavior bottomSheetBehavior;
    GestureDetectorCompat gestureDetector;
    boolean clickedStart = false;
    boolean clickedEnd = false;
    boolean clickedStart1 = false;
    boolean clickedEnd1 = false;
    String time1, time2;
    java.util.Date d3;
    java.util.Date d4;
    java.util.Date d1 = null;
    java.util.Date d2 = null;
    private Calendar rightNow = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private String formattedString = simpleDateFormat1.format(rightNow.getTime());
    private int year = Integer.parseInt(formattedString);
    private MainConstraintBinding binding;

    private static String formatting(long s) {
        if (s < 10) return "0" + s;
        else return "" + s;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainConstraintBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        assert binding.statusTextView != null;
        registerForContextMenu(binding.statusTextView);

        assert binding.collapsingRelativeLayout != null;
        bottomSheetBehavior = BottomSheetBehavior.from(binding.collapsingRelativeLayout);

        assert binding.startTimeTextView != null;
        binding.startTimeTextView.setOnClickListener(view1 -> {
            clickedStart = true;
            showTimePickerDialog(view1);
        });

        assert binding.endTimeTextView != null;
        binding.endTimeTextView.setOnClickListener(view2 -> {
            clickedEnd = true;
            showTimePickerDialog(view2);
        });

        gestureDetector = new GestureDetectorCompat(this, new LearnGesture());

        Window window = MainActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimaryDark));

        Observer<List<Date>> monthDates = dates -> {
            long workedLong01 = 0;
            long workedLong02 = 0;
            long workedLong03 = 0;
            long workedLong04 = 0;
            long workedLong05 = 0;
            long workedLong06 = 0;

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

            if (dates.size() == 42) {
                for (int j = 0; j < 7; j++) {
                    Date testDate = dates.get(j);
                    int testStatus = testDate.getStatus();
                    if (testStatus == 2) {
                        String[] arr = testDate.getHours().split(":");
                        workedLong01 += 60 * Integer.parseInt(arr[1]);
                        workedLong01 += 3600 * Integer.parseInt(arr[0]);
                    }
                }

                for (int j = 7; j < 14; j++) {
                    Date testDate = dates.get(j);
                    int testStatus = testDate.getStatus();
                    if (testStatus == 2) {
                        String[] arr = testDate.getHours().split(":");
                        workedLong02 += 60 * Integer.parseInt(arr[1]);
                        workedLong02 += 3600 * Integer.parseInt(arr[0]);
                    }
                }

                for (int j = 14; j < 21; j++) {
                    Date testDate = dates.get(j);
                    int testStatus = testDate.getStatus();
                    if (testStatus == 2) {
                        String[] arr = testDate.getHours().split(":");
                        workedLong03 += 60 * Integer.parseInt(arr[1]);
                        workedLong03 += 3600 * Integer.parseInt(arr[0]);
                    }
                }

                for (int j = 21; j < 28; j++) {
                    Date testDate = dates.get(j);
                    int testStatus = testDate.getStatus();
                    if (testStatus == 2) {
                        String[] arr = testDate.getHours().split(":");
                        workedLong04 += 60 * Integer.parseInt(arr[1]);
                        workedLong04 += 3600 * Integer.parseInt(arr[0]);
                    }
                }

                for (int j = 28; j < 35; j++) {
                    Date testDate = dates.get(j);
                    int testStatus = testDate.getStatus();
                    if (testStatus == 2) {
                        String[] arr = testDate.getHours().split(":");
                        workedLong05 += 60 * Integer.parseInt(arr[1]);
                        workedLong05 += 3600 * Integer.parseInt(arr[0]);
                    }
                }

                for (int j = 35; j < 42; j++) {
                    Date testDate = dates.get(j);
                    int testStatus = testDate.getStatus();
                    if (testStatus == 2) {
                        String[] arr = testDate.getHours().split(":");
                        workedLong06 += 60 * Integer.parseInt(arr[1]);
                        workedLong06 += 3600 * Integer.parseInt(arr[0]);
                    }
                }
                String firstWeek01 = DatesGenerator.normalDate(dates.get(0).getDate());
                String firstWeek02 = DatesGenerator.normalDate(dates.get(6).getDate());

                String secondWeek01 = DatesGenerator.normalDate(dates.get(7).getDate());
                String secondWeek02 = DatesGenerator.normalDate(dates.get(13).getDate());

                String thirdWeek01 = DatesGenerator.normalDate(dates.get(14).getDate());
                String thirdWeek02 = DatesGenerator.normalDate(dates.get(20).getDate());

                String forthWeek01 = DatesGenerator.normalDate(dates.get(21).getDate());
                String forthWeek02 = DatesGenerator.normalDate(dates.get(27).getDate());

                String fifthWeek01 = DatesGenerator.normalDate(dates.get(28).getDate());
                String fifthWeek02 = DatesGenerator.normalDate(dates.get(34).getDate());

                String sixthWeek01 = DatesGenerator.normalDate(dates.get(35).getDate());
                String sixthWeek02 = DatesGenerator.normalDate(dates.get(41).getDate());

                String hoursWorkedString01 = DatesGenerator.hoursWorkedString(workedLong01);
                String string = firstWeek01 + " - " + firstWeek02 + "  " + hoursWorkedString01;
                assert binding.firstWeekTextView != null;
                binding.firstWeekTextView.setText(string);
                Log.d("MainActivity", firstWeek01 + " - "
                        + firstWeek02 + "  " + hoursWorkedString01);

                String hoursWorkedString02 = DatesGenerator.hoursWorkedString(workedLong02);
                String string02 = secondWeek01 + " - " + secondWeek02 + "  " + hoursWorkedString02;
                assert binding.secondWeekTextView != null;
                binding.secondWeekTextView.setText(string02);

                String hoursWorkedString03 = DatesGenerator.hoursWorkedString(workedLong03);
                String string03 = thirdWeek01 + " - " + thirdWeek02 + "  " + hoursWorkedString03;
                assert binding.thirdWeekTextView != null;
                binding.thirdWeekTextView.setText(string03);

                String hoursWorkedString04 = DatesGenerator.hoursWorkedString(workedLong04);
                String string04 = forthWeek01 + " - " + forthWeek02 + "  " + hoursWorkedString04;
                assert binding.forthWeekTextView != null;
                binding.forthWeekTextView.setText(string04);

                String hoursWorkedString05 = DatesGenerator.hoursWorkedString(workedLong05);
                String string05 = fifthWeek01 + " - " + fifthWeek02 + "  " + hoursWorkedString05;
                assert binding.fifthWeekTextView != null;
                binding.fifthWeekTextView.setText(string05);

                String hoursWorkedString06 = DatesGenerator.hoursWorkedString(workedLong06);
                String string06 = sixthWeek01 + " - " + sixthWeek02 + "  " + hoursWorkedString06;
                assert binding.sixthWeekTextView != null;
                binding.sixthWeekTextView.setText(string06);
            }
            dateAdapter.setDates(dates, monthInt);
        };

        Observer<List<Date>> hoursObserver = dates -> {

            long workLong = 0;
            long workedLong = 0;
            for (int i = 0; i < dates.size(); i++) {

                Date date = dates.get(i);
                int status = date.getStatus();
                if (status == 2) {
                    String[] arr = date.getHours().split(":");
                    workedLong += 60 * Integer.parseInt(arr[1]);
                    workedLong += 3600 * Integer.parseInt(arr[0]);
                }

                if (status == 1) {
                    String[] arr = date.getHours().split(":");
                    workLong += 60 * Integer.parseInt(arr[1]);
                    workLong += 3600 * Integer.parseInt(arr[0]);
                }
            }

            long hhWorked = workedLong / 3600;
            workedLong %= 3600;
            long mmWorked = workedLong / 60;
            String hoursWorkedString = formatting(hhWorked) + ":" + formatting(mmWorked);
//            binding.hoursWorkedNumberTextView.setText(hoursWorkedString);

            long hhWork = workLong / 3600;
            workLong %= 3600;
            long mmWork = workLong / 60;
            String hoursBookedString = formatting(hhWork) + ":" + formatting(mmWork);
//            binding.hoursBookedNumberTextView.setText(hoursBookedString);
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

//                            assert binding.notesEditText != null;
//                            binding.notesEditText.setText("");

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
//                                String notesString = dateAdapter.
//                                        getItemNotesInPosition(Integer.parseInt(searchItems.get(0)));
//                                binding.notesEditText.setText(notesString);

                                Configuration config = getResources().getConfiguration();
                                int screenHeight = config.screenHeightDp;

                                if (screenHeight < 700) {
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                } else {
                                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                }
                            }
                            binding.saveButton.setOnClickListener(view12 -> {
                                if (clickedStart1 && clickedEnd1) {
                                    long diff = d4.getTime() - d3.getTime();
                                    long diffMinutes = diff / (60 * 1000) % 60;
                                    long diffHours = diff / (60 * 60 * 1000) % 24;
                                    @SuppressLint("DefaultLocale")
                                    String hoursChangedValueH = String.format("%02d", diffHours);
                                    @SuppressLint("DefaultLocale")
                                    String hoursChangedValueM = String.format("%02d", diffMinutes);
                                    hoursChangedValue = hoursChangedValueH + ":" + hoursChangedValueM;
                                    Log.d("MainActivity", "hoursChangedValue " + hoursChangedValue);
                                    Log.d("MainActivity", "diffHours " + diffHours + "diffMinutes " + diffMinutes);
                                    clickedStart1 = false;
                                    clickedEnd1 = false;
                                }
                                DatesGenerator.saveInit(statusChangedValue, hoursChangedValue,
                                        datesList, mDateViewModel, tracker, getApplicationContext(),
                                        getResources());
                                binding.startTimeTextView.setHint(getResources().getString(R.string.start_time));
                                binding.endTimeTextView.setHint(getResources().getString(R.string.end_time));
                            });
                        } else {
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                        }
                    }

                    @Override
                    public void onItemStateChanged(@NonNull Long key, boolean selected) {
                        super.onItemStateChanged(key, selected);
                    }
                });
    }

    public void processTimePickerResult(int hourOfDay, int minute) throws ParseException {
        @SuppressLint("DefaultLocale")
        String time = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);

        if (clickedStart) {
            assert binding.startTimeTextView != null;
            binding.startTimeTextView.setText(time);
            time1 = time;
            d1 = simpleDateFormat2.parse(time1);
            d3 = d1;
            Log.d("MainActivity", "time1 " + time1);
            Log.d("MainActivity", "d1 d2 " + d1 + d2);
            clickedStart1 = true;
            clickedStart = false;
        }

        if (clickedEnd) {
            assert binding.endTimeTextView != null;
            binding.endTimeTextView.setText(time);
            time2 = time;
            d2 = simpleDateFormat2.parse(time2);
            d4 = d2;
            Log.d("MainActivity", "time2 " + time2);
            Log.d("MainActivity", "d2 d1" + d2 + d1);
            clickedEnd1 = true;
            clickedEnd = false;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_text_view, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_will_work:
                assert binding.statusTextView != null;
                binding.statusTextView.setText(R.string.will_work);
                statusChangedValue = 1;
                return true;
            case R.id.menu_have_worked:
                assert binding.statusTextView != null;
                binding.statusTextView.setText(R.string.have_worked);
                statusChangedValue = 2;
                return true;
            case R.id.menu_holiday:
                assert binding.statusTextView != null;
                binding.statusTextView.setText(R.string.holiday);
                statusChangedValue = 3;
                return true;
            case R.id.menu_clear:
                assert binding.statusTextView != null;
                binding.statusTextView.setText(R.string.cancel);
                statusChangedValue = 0;
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
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
