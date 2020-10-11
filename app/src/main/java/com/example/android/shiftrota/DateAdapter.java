package com.example.android.shiftrota;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.example.android.shiftrota.selection.Details;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Date> mDates;
    private Context context;
    private int mMonthInt;
    private SelectionTracker<Long> tracker;


    DateAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    void setSelectionTracker(SelectionTracker<Long> selectionTracker) {
        this.tracker = selectionTracker;
    }

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (mDates != null) {
            Date current = mDates.get(position);
            ((ViewHolder) holder).bind(current, position);
        }
    }

    void setDates(List<Date> dates, int monthInt) {
        mDates = dates;
        mMonthInt = monthInt;
        notifyDataSetChanged();
    }

    String getItemInPosition(int position) {
        Date date = mDates.get(position);
        return date.getDate();
    }

    @Override
    public int getItemCount() {
        if (mDates != null)
            return mDates.size();
        else return 0;
    }

    int getItemStatusInPosition(int position) {
        Date date = mDates.get(position);
        return date.getStatus();
    }

    String getItemStartInPosition(int position) {
        Date date = mDates.get(position);
        return date.getStartTime();
    }

    String getItemEndInPosition(int position) {
        Date date = mDates.get(position);
        return date.getEndTime();
    }

    String getItemHoursInPosition(int position) {
        Date date = mDates.get(position);
        return date.getHours();
    }

    String getItemLunchBreakInPosition(int position) {
        Date date = mDates.get(position);
        return date.getLunchBreak();
    }

    String getItemNotesInPosition(int position) {
        Date date = mDates.get(position);
        return date.getNotes();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final Details details;
        private final MaterialCardView materialCardView;
        TextView dayTextView, startTimeTextView, endTimeTextVIew, notesTextView, totalHoursTextView;
        ConstraintLayout itemLayout;

        ViewHolder(View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.material_card_view);
            dayTextView = itemView.findViewById(R.id.day_text_view);
            startTimeTextView = itemView.findViewById(R.id.start_time_text_view);
            endTimeTextVIew = itemView.findViewById(R.id.end_time_text_view);
            notesTextView = itemView.findViewById(R.id.notes_text_view);
            totalHoursTextView = itemView.findViewById(R.id.total_hours_text_view);
            itemLayout = itemView.findViewById(R.id.item_layout);
            details = new Details();
        }

        private void bind(Date date, int position) {

            Configuration config = context.getResources().getConfiguration();
            int screenHeight = config.screenHeightDp;
//            if (screenHeight < 700) {
//                materialCardView.getLayoutParams().height = 49;
//            }

            details.position = position;

            int klein = Integer.parseInt(DatesGenerator.midTwo(date.getDate()));

            int statusInt = date.getStatus();

            if (klein == mMonthInt) {

                materialCardView.setChecked(tracker.isSelected(details.getSelectionKey()));

                switch (statusInt) {
                    case 0:
                        materialCardView.setBackgroundResource(R.drawable.cell_shape_unchecked);
                        break;
                    case 1:
                        materialCardView.setBackgroundResource(R.drawable.cell_shape_will_work);
                        break;
                    case 2:
                        materialCardView.setBackgroundResource(R.drawable.cell_shape_have_worked);
                        break;
                    case 3:
                        materialCardView.setBackgroundResource(R.drawable.cell_shape_holiday);
                        break;
                }

                String currentDate = date.getDate();

                if (currentDate.equals(DatesGenerator.todayTheOtherWay())) {
                    dayTextView.setTextColor(ContextCompat.getColor(context, R.color.today_color));
                }
            } else {
                materialCardView.setBackgroundResource(R.drawable.cell_shape_not_current);
            }

            String notesString = date.getNotes();
            if (!(notesString == null) && notesString.length() > 6) {
                String firstSevenChars = notesString.substring(0, 6);
                notesTextView.setText(firstSevenChars);
            } else {
                notesTextView.setText(notesString);
            }

            if (startTimeTextView != null) {
                if (date.getHours().equals("00:00") || date.getHours() == null) {
                    startTimeTextView.setVisibility(View.GONE);
                    endTimeTextVIew.setVisibility(View.GONE);
                } else {
                    startTimeTextView.setText(date.getStartTime());
                    startTimeTextView.setVisibility(View.VISIBLE);
                    endTimeTextVIew.setText(date.getEndTime());
                    endTimeTextVIew.setVisibility(View.VISIBLE);
                }
            }

            if (totalHoursTextView != null) {
                if (date.getHours().equals("00:00") || date.getHours() == null) {
                    totalHoursTextView.setVisibility(View.GONE);
                } else  {
                    totalHoursTextView.setText(date.getHours());
                }

            }

            dayTextView.setText(DatesGenerator.lastTwo(date.getDate()));
        }

        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return details;
        }
    }
}
