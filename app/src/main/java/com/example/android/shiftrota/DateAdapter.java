package com.example.android.shiftrota;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;
import com.example.android.shiftrota.selection.Details;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Date> mDates;
    private Context context;
    private AdapterCallback mAdapterCallback;
    private int indexInt = -1;
    private int tempPosition = -1;
    private int mMonthInt;
    private SelectionTracker<Long> tracker;


    DateAdapter(Context context) {
        try {
            this.mAdapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
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

            int klein = Integer.parseInt(DatesGenerator.midTwo(current.getDate()));
            Log.d("DATEADAPTER", "KLEIN " + klein);

            int statusInt = current.getStatus();

            if (indexInt == position) {
                ((ViewHolder) holder).dayTextView.setBackgroundResource(R.drawable.cell_shape_check);
            } else if (klein != mMonthInt) {
                ((ViewHolder) holder).dayTextView.setBackgroundResource(R.drawable.cell_shape_not_current);
            } else {
                switch (statusInt) {
                    case 0:
                        ((ViewHolder) holder).dayTextView.setBackgroundResource(R.drawable.cell_shape_unchecked);
                        break;
                    case 1:
                        ((ViewHolder) holder).dayTextView.setBackgroundResource(R.drawable.cell_shape_will_work);
                        break;
                    case 2:
                        ((ViewHolder) holder).dayTextView.setBackgroundResource(R.drawable.cell_shape_have_worked);
                        break;
                    case 3:
                        ((ViewHolder) holder).dayTextView.setBackgroundResource(R.drawable.cell_shape_holiday);
                        break;
                }
            }

            ((ViewHolder) holder).dayTextView.setText(DatesGenerator.lastTwo(current.getDate()));

            ((ViewHolder) holder).hoursWorkedTextView.setText(current.getHours());

            String currentDate = current.getDate();

            if (currentDate.equals(DatesGenerator.todayTheOtherWay())) {
                ((ViewHolder) holder).dayTextView.setTextColor(ContextCompat.getColor(context, R.color.today_color));
            }

        } else {
            ((ViewHolder) holder).dayTextView.setText(com.example.android.shiftrota.R.string.no_data);
        }

        ((ViewHolder) holder).dayTextView.setOnClickListener(v -> {

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

        private final Details details;
        private final MaterialCardView materialCardView;
        TextView dayTextView, hoursWorkedTextView;

        ViewHolder(View itemView) {
            super(itemView);
            materialCardView = itemView.findViewById(R.id.item_card);
            dayTextView = itemView.findViewById(R.id.item_text_view_main);
            hoursWorkedTextView = itemView.findViewById(R.id.item_text_view_details);
            details = new Details();
        }

        private void bind(Date date, int position) {
            details.position = position;
            if (tracker != null) {
                bindSelectedState();
            }
        }

        private void bindSelectedState() {
            materialCardView.setChecked(tracker.isSelected(details.getSelectionKey()));
        }

        public ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return details;
        }

    }
}
