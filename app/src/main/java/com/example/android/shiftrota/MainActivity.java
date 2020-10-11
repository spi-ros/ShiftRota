package com.example.android.shiftrota;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.core.view.GestureDetectorCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.example.android.shiftrota.selection.DetailsLookup;
import com.example.android.shiftrota.selection.KeyProvider;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    static final int NUMBER_OF_COLUMNS = 7;
    int monthInt = DatesGenerator.getMonthInt();
    DateAdapter dateAdapter;
    SelectionTracker<Long> tracker;
    DateViewModel mDateViewModel;
    int statusChangedValue;
    String hoursChangedValue;
    GestureDetectorCompat gestureDetector;
    boolean sizeBool = false;
    boolean clickedStart = false;
    boolean clickedEnd = false;
    boolean clickedStart1 = false;
    boolean clickedEnd1 = false;
    String time1, time2;
    java.util.Date d3;
    java.util.Date d4;
    java.util.Date d1 = null;
    java.util.Date d2 = null;
    boolean bool;
    //    float initialX;
    ConstraintLayout constraintLayout;
    ScrollView scrollView;
    TextView startTimeTextView, endTimeTextView, statusTextView, firstWeekTextView,
            secondWeekTextView, thirdWeekTextView, fourthWeekTextView, fifthWeekTextView,
            sixthWeekTextView, monthTextView, selectedTextView;
    TextInputEditText notesEditText;
    EditText breakEditText;
    MaterialButton saveButton;
    ImageView saveNotesImageView, upDownImageView01, clearImageView, notesImageView;
    String notesString, breakString;
    RelativeLayout notesRelative, saveRelative, collapsingRelativeLayout;
    View scrimView;
    RecyclerView recyclerView;
    long diff;
    private AdView mAdView;
    private BottomSheetBehavior bottomSheetBehavior;
    private Calendar rightNow = Calendar.getInstance();
    private SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy", Locale.ENGLISH);
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private String formattedString = simpleDateFormat1.format(rightNow.getTime());
    private int year = Integer.parseInt(formattedString);
//    private MainConstraintBinding binding;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = MainConstraintBinding.inflate(getLayoutInflater());
//        View view = binding.getRoot();
        setContentView(R.layout.main_constraint);

//        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.scene_root);

        constraintLayout = findViewById(R.id.constraint_layout);
        scrollView = findViewById(R.id.scroll_view);
        collapsingRelativeLayout = findViewById(R.id.collapsing_relative_layout);
        scrimView = findViewById(R.id.scrim_view);
        upDownImageView01 = findViewById(R.id.up_down_image_view_01);
        clearImageView = findViewById(R.id.clear_image_view);
        firstWeekTextView = findViewById(R.id.first_week_text_view);
        secondWeekTextView = findViewById(R.id.second_week_text_view);
        thirdWeekTextView = findViewById(R.id.third_week_text_view);
        fourthWeekTextView = findViewById(R.id.fourth_week_text_view);
        fifthWeekTextView = findViewById(R.id.fifth_week_text_view);
        sixthWeekTextView = findViewById(R.id.sixth_week_text_view);
        notesEditText = findViewById(R.id.notes_edit_text);
        notesImageView = findViewById(R.id.notes_image_view);
        startTimeTextView = findViewById(R.id.start_time_text_view);
        endTimeTextView = findViewById(R.id.end_time_text_view);
        breakEditText = findViewById(R.id.break_edit_text);
        statusTextView = findViewById(R.id.status_text_view);
        saveButton = findViewById(R.id.save_button);
        saveNotesImageView = findViewById(R.id.save_notes);
        notesRelative = findViewById(R.id.notes_relative);
        saveRelative = findViewById(R.id.save_relative);
        recyclerView = findViewById(R.id.recycler_view);
        monthTextView = findViewById(R.id.month_text_view);
        selectedTextView = findViewById(R.id.selected_text_view);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        notesImageView.setBackground(getDrawable(R.drawable.ic_baseline_notes_24));
        notesImageView.setOnClickListener(view1 -> {
            notesRelative.setVisibility(View.VISIBLE);
            saveRelative.setVisibility(View.GONE);
        });

        saveNotesImageView.setOnClickListener(view1 -> {
            notesRelative.setVisibility(View.GONE);
            saveRelative.setVisibility(View.VISIBLE);
            notesString = Objects.requireNonNull(notesEditText.getText()).toString();
        });

        bottomSheetBehavior = BottomSheetBehavior.from(collapsingRelativeLayout);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    scrimView.setVisibility(View.VISIBLE);
                    mAdView.setVisibility(View.GONE);
                    bool = true;
                }

                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mAdView.setVisibility(View.GONE);
                }

                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mAdView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                scrimView.setVisibility(View.VISIBLE);
                scrimView.setAlpha(slideOffset);
                upDownImageView01.setRotation(slideOffset * 180);
            }
        });

        upDownImageView01.setOnClickListener(view1 -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }

            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        clearImageView.setOnClickListener(view1 -> {
            if (tracker.hasSelection()) {
                tracker.clearSelection();
                startTimeTextView.setText(getResources().getString(R.string.start_time));
                endTimeTextView.setText(getResources().getString(R.string.end_time));
                statusTextView.setText(getResources().getString(R.string.set_status));
            }
        });

        startTimeTextView.setOnClickListener(view1 -> {
            clickedStart = true;
            showTimePickerDialog(view1);
        });

        endTimeTextView.setOnClickListener(view2 -> {
            clickedEnd = true;
            showTimePickerDialog(view2);
        });

        gestureDetector = new GestureDetectorCompat(this, new LearnGesture());

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

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
                            2, date.getStartTime(), date.getEndTime(), date.getHours(),
                            date.getLunchBreak(), date.getNotes());
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
                String hoursWorkedString01 = DatesGenerator.hoursWorkedString(workedLong01, firstWeek01, firstWeek02);

                firstWeekTextView.setText(hoursWorkedString01);

                String secondWeek01 = DatesGenerator.normalDate(dates.get(7).getDate());
                String secondWeek02 = DatesGenerator.normalDate(dates.get(13).getDate());
                String hoursWorkedString02 = DatesGenerator.hoursWorkedString(workedLong02, secondWeek01, secondWeek02);
                secondWeekTextView.setText(hoursWorkedString02);

                String thirdWeek01 = DatesGenerator.normalDate(dates.get(14).getDate());
                String thirdWeek02 = DatesGenerator.normalDate(dates.get(20).getDate());
                String hoursWorkedString03 = DatesGenerator.hoursWorkedString(workedLong03, thirdWeek01, thirdWeek02);
                thirdWeekTextView.setText(hoursWorkedString03);

                String forthWeek01 = DatesGenerator.normalDate(dates.get(21).getDate());
                String forthWeek02 = DatesGenerator.normalDate(dates.get(27).getDate());
                String hoursWorkedString04 = DatesGenerator.hoursWorkedString(workedLong04, forthWeek01, forthWeek02);
                fourthWeekTextView.setText(hoursWorkedString04);

                String fifthWeek01 = DatesGenerator.normalDate(dates.get(28).getDate());
                String fifthWeek02 = DatesGenerator.normalDate(dates.get(34).getDate());
                String hoursWorkedString05 = DatesGenerator.hoursWorkedString(workedLong05, fifthWeek01, fifthWeek02);
                fifthWeekTextView.setText(hoursWorkedString05);

                String sixthWeek01 = DatesGenerator.normalDate(dates.get(35).getDate());
                String sixthWeek02 = DatesGenerator.normalDate(dates.get(41).getDate());
                String hoursWorkedString06 = DatesGenerator.hoursWorkedString(workedLong06, sixthWeek01, sixthWeek02);
                sixthWeekTextView.setText(hoursWorkedString06);
            }
            dateAdapter.setDates(dates, monthInt);
        };

        ViewModelProvider modelProvider = new ViewModelProvider(this);
        mDateViewModel = modelProvider.get(DateViewModel.class);

        mDateViewModel.getAnotherMonth().observe(this, monthDates);
        mDateViewModel.setInputMonth(monthInt);

        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt, year));

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

        tracker.addObserver(
                new SelectionTracker.SelectionObserver<Long>() {
                    @Override
                    public void onSelectionChanged() {
                        if (tracker.getSelection().size() > 0) {


                            int selectionSizeInt = tracker.getSelection().size();

                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

                            String trackerSelectionString = tracker.getSelection().toString();
                            String search = trackerSelectionString.
                                    substring(trackerSelectionString.indexOf("[") + 1,
                                            trackerSelectionString.indexOf("]"));
                            List<String> searchItems = Arrays.asList(search.split(", "));
                            List<String> datesList = new ArrayList<>();

                            if (selectionSizeInt == 1) {
                                notesString = dateAdapter.
                                        getItemNotesInPosition(Integer.parseInt(searchItems.get(0)));
                                notesEditText.setText(notesString);

                                if (notesString != null && !notesString.equals("")) {
                                    notesImageView.setBackground(getResources().
                                            getDrawable(R.drawable.ic_baseline_notes_green_24));
                                }

                                statusChangedValue = dateAdapter.
                                        getItemStatusInPosition(Integer.parseInt(searchItems.get(0)));

                                switch (statusChangedValue) {
                                    case 0:
                                        statusTextView.setText(R.string.cancel);
                                        break;
                                    case 1:
                                        statusTextView.setText(R.string.will_work);
                                        break;
                                    case 2:
                                        statusTextView.setText(R.string.have_worked);
                                        break;
                                    case 3:
                                        statusTextView.setText(R.string.holiday);
                                        break;
                                }

                                time1 = dateAdapter.
                                        getItemStartInPosition(Integer.parseInt(searchItems.get(0)));
                                startTimeTextView.setText(time1);

                                time2 = dateAdapter.
                                        getItemEndInPosition(Integer.parseInt(searchItems.get(0)));
                                endTimeTextView.setText(time2);

                                String klein = dateAdapter.
                                        getItemLunchBreakInPosition(Integer.parseInt(searchItems.get(0)));
                                Log.d("MainActivity", "klein " + klein);
                                breakEditText.setText(klein);

                                hoursChangedValue = dateAdapter.
                                        getItemHoursInPosition(Integer.parseInt(searchItems.get(0)));
                                sizeBool = true;
                                clickedStart1 = true;
                                clickedEnd1 = true;

                            } else {
                                notesEditText.setText("");
                                statusTextView.setText("");
                                startTimeTextView.setText("");
                                endTimeTextView.setText("");
                                breakEditText.setText("");
                                notesImageView.setBackground(getResources().
                                        getDrawable(R.drawable.ic_baseline_notes_24));
                                sizeBool = false;
                            }

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
                            selectedTextView.setText(String.format(getResources().
                                    getString(R.string.days_selected_sum), selectionSizeString));


                            saveButton.setOnClickListener(view12 -> {

                                breakString = String.valueOf(breakEditText.getText());

                                if (clickedStart1 && clickedEnd1 &&
                                        statusChangedValue != 0 && statusChangedValue != 3) {

                                    if (breakString.equals("")) {
                                        breakString = getString(R.string.minutes_00);
                                    }

                                    if (time1 != null && !time1.equals("") &&
                                            time2 != null && !time2.equals("")) {

                                        if (sizeBool) {
                                            try {
                                                d4 = simpleDateFormat2.parse(time2);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                            try {
                                                d3 = simpleDateFormat2.parse(time1);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        int testInt = Integer.parseInt(breakString);
                                        diff = d4.getTime() - d3.getTime() - TimeUnit.MINUTES.toMillis(testInt);
                                        long diffMinutes = diff / (60 * 1000) % 60;
                                        long diffHours = diff / (60 * 60 * 1000) % 24;
                                        @SuppressLint("DefaultLocale")
                                        String hoursChangedValueH = String.format("%02d", diffHours);
                                        @SuppressLint("DefaultLocale")
                                        String hoursChangedValueM = String.format("%02d", diffMinutes);
                                        hoursChangedValue = hoursChangedValueH + ":" + hoursChangedValueM;
                                        clickedStart1 = false;
                                        clickedEnd1 = false;
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Please add Start Time and End Time",
                                                Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }

                                DatesGenerator.saveInit(statusChangedValue, hoursChangedValue,
                                        time1, time2, breakString, notesString, datesList,
                                        mDateViewModel, tracker, getApplicationContext(),
                                        getResources());
                                startTimeTextView.setText("");
                                endTimeTextView.setText("");
                                statusTextView.setText("");
                                breakEditText.setText("");
                                Log.d("MainActivity", "saveBreakString " + breakString);
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
            startTimeTextView.setText(time);
            time1 = time;
            d1 = simpleDateFormat2.parse(time1);
            d3 = d1;
            Log.d("MainActivity", "time1 " + time1);
            Log.d("MainActivity", "d1 d2 " + d1 + d2);
            clickedStart1 = true;
            clickedStart = false;
        }

        if (clickedEnd) {
            endTimeTextView.setText(time);
            time2 = time;
            d2 = simpleDateFormat2.parse(time2);
            d4 = d2;
            Log.d("MainActivity", "time2 " + time2);
            Log.d("MainActivity", "d2 d1" + d2 + d1);
            clickedEnd1 = true;
            clickedEnd = false;
        }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.inflate(R.menu.action_text_view);
        popup.show();
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_will_work:
                    statusTextView.setText(R.string.will_work);
                    statusChangedValue = 1;
                    return true;
                case R.id.menu_have_worked:
                    statusTextView.setText(R.string.have_worked);
                    statusChangedValue = 2;
                    return true;
                case R.id.menu_holiday:
                    statusTextView.setText(R.string.holiday);
                    statusChangedValue = 3;
                    return true;
                case R.id.menu_clear:
                    statusTextView.setText(R.string.cancel);
                    statusChangedValue = 0;
                    return true;
                default:
                    return false;
            }
        });
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
        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt, year));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDateViewModel.setInputMonth(monthInt);
        monthTextView.setText(DatesGenerator.nameOfMonth(monthInt, year));
    }

    @Override
    public void onBackPressed() {
        if (tracker.hasSelection()) {
            tracker.clearSelection();
            startTimeTextView.setText(getResources().getString(R.string.start_time));
            endTimeTextView.setText(getResources().getString(R.string.end_time));
            statusTextView.setText(getResources().getString(R.string.set_status));
            breakEditText.setText(getString(R.string.minutes));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

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
                monthTextView.setText(DatesGenerator.nameOfMonth(monthInt, year));
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
                monthTextView.setText(DatesGenerator.nameOfMonth(monthInt, year));
            }
            return true;
        }
    }
}
