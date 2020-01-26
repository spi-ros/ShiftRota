package com.example.android.shiftrota;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;

import java.util.ArrayList;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Date> mDates;
    private Context context;
    private AdapterCallback mAdapterCallback;
    private int indexInt = -1;
    private int tempPosition = -1;
    private int mMonthInt;

    List<Integer> list = new ArrayList<>();
    SelectionTracker<Long> tracker;
//    private Activity activity;
//    private int spinnerStatusInt;
//    private Spinner statusSpinner;


    DateAdapter(Context context) {
        try {
            this.mAdapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
        mInflater = LayoutInflater.from(context);
        this.context = context;
//        this.activity = activity;
    }

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final DateAdapter.ViewHolder holder, final int position) {

        Log.d("DATEADAPTER", "MONTHIN " + mMonthInt);
        if (mDates != null) {


            holder.bind();

            Date current = mDates.get(position);

            int statusInt = current.getStatus();

            int klein = Integer.parseInt(DatesGenerator.midTwo(current.getDate()));
            Log.d("DATEADAPTER", "KLEIN " + klein);

//            if (indexInt == position) {
//                holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_check);
//            } else if (klein != mMonthInt) {
//                holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_not_current);
//            } else {
//                switch (statusInt) {
//                    case 0:
//                        holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_unchecked);
//                        break;
//                    case 1:
//                        holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_will_work);
//                        break;
//                    case 2:
//                        holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_have_worked);
//                        break;
//                    case 3:
//                        holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_holiday);
//                        break;
//                }
//            }

            holder.dayTextView.setText(DatesGenerator.lastTwo(current.getDate()));

            holder.hoursWorkedTextView.setText(current.getHours());

            String currentDate = current.getDate();

            if (currentDate.equals(DatesGenerator.todayTheOtherWay())) {
                holder.dayTextView.setTextColor(ContextCompat.getColor(context, R.color.today_color));
            }

        } else {
            holder.dayTextView.setText(com.example.android.shiftrota.R.string.no_data);
        }

//        final boolean[] isSelected = {false};

//        final ActionMode[] currentActionMode = new ActionMode[1];
//
//        ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
//            @Override
//            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                MenuInflater inflater = mode.getMenuInflater();
//                inflater.inflate(R.menu.action_text_view, menu);
//                return true;
//            }
//
//            @Override
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                return false;
//            }
//
//            @Override
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.menu_clear:
//                        return true;
//                    case R.id.menu_will_work:
//                        return true;
//                    case R.id.menu_have_worked:
//                        return true;
//                    case R.id.menu_holiday:
//                        return true;
//                    case R.id.menu_select_hours:
//                        return true;
//                    case R.id.menu_notes:
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//
//            @Override
//            public void onDestroyActionMode(ActionMode mode) {
//                currentActionMode[0] = null;
//            }
//        };
//
//        holder.dayTextView.setOnLongClickListener(v -> {
//            if (currentActionMode[0] != null) {
//                return false;
//            }
//            currentActionMode[0] = activity.startActionMode(actionModeCallback);
//            v.setSelected(true);
//            return true;
//        });

        holder.dayTextView.setOnClickListener(v -> {

            indexInt = position;
            notifyDataSetChanged();

            Date current = mDates.get(position);
            String dateString = current.getDate();
            String hoursString = String.valueOf(current.getHours());
            String notesString = current.getNotes();
            int statusInt = current.getStatus();
            mAdapterCallback.onMethodCallback(dateString, hoursString, notesString, statusInt);
            Toast.makeText(context.getApplicationContext(), "You clicked " +
                    dateString, Toast.LENGTH_SHORT).show();
        });

//        if (indexInt == position) {
//            holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_check);
//        } else if (klein != DatesGenerator.getMonthInt()) {
//            holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_not_current);
//        } else {
//            Date current = mDates.get(holder.getAdapterPosition());
//
//            int testInt = current.getStatus();
//
//            switch (testInt) {
//                case 0:
//                    holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_unchecked);
//                    break;
//                case 1:
//                    holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_will_work);
//                    break;
//                case 2:
//                    holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_have_worked);
//                    break;
//                case 3:
//                    holder.dayTextView.setBackgroundResource(R.drawable.cell_shape_holiday);
//                    break;
//            }
//        }
    }

    void setDates(List<Date> dates, int monthInt) {
        mDates = dates;
        mMonthInt = monthInt;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDates != null)
            return mDates.size();
        else return 0;
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    public interface AdapterCallback {
        void onMethodCallback(String yourValue1, String yourValue2, String yourValue3, int statusInt);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dayTextView, hoursWorkedTextView;

        void bind() {
            itemView.isActivated();
        }

        public ItemDetailsLookup.ItemDetails<Long> getItemDetails(){
            return new ItemDetailsLookup.ItemDetails<Long>(){
                @Override
                public int getPosition() {
                    return getAdapterPosition();
                }

                @NonNull
                @Override
                public Long getSelectionKey() {
                    return getItemId();
                }
            };
        }


        ViewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.item_text_view_main);
            hoursWorkedTextView = itemView.findViewById(R.id.item_text_view_details);
        }
    }

//    private void setUpSpinner() {
//
//        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(activity,
//                R.array.array_hours_options, android.R.layout.simple_spinner_item);
//
//        statusSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//
//        statusSpinner.setAdapter(statusSpinnerAdapter);
//
//        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String selection = (String) parent.getItemAtPosition(position);
//                if (!selection.isEmpty()) {
//                    switch (selection) {
//                        case "Clear":
//                            spinnerStatusInt = 0;
//                            break;
//                        case "Work":
//                            spinnerStatusInt = 1;
//                            break;
//                        case "Worked":
//                            spinnerStatusInt = 2;
//                            break;
//                        case "Holiday":
//                            spinnerStatusInt = 3;
//                            break;
//                    }
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                spinnerStatusInt = 0;
//            }
//        });
//    }
}
