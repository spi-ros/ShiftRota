package com.example.android.shiftrota;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private ArrayList<Double> hoursArray = new ArrayList<>();
    private DateViewModel dateViewModel;
    private final LayoutInflater mInflater;
    private List<Date> mDates;
    private Context context;
    private AdapterCallback mAdapterCallback;
    private Activity activity;
    private int indexInt = -1;
    private int tempPosition = -1;


    DateAdapter(Context context, Activity activity) {
        try {
            this.mAdapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final DateAdapter.ViewHolder holder, final int position) {

        if (mDates != null) {

            Date current = mDates.get(position);

            int testInt = current.getStatus();
//            String dateString = DatesGenerator.midTwo(current.getDate());
//            if (dateString.compareTo("01") > 0) {
//                holder.textViewA.setBackgroundResource(R.drawable.cell_shape_other_month);
//            }
            switch (testInt) {
                case 0:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_unchecked);
                    break;
                case 1:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_will_work);
                    break;
                case 2:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_have_worked);
                    break;
                case 3:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_holiday);
                    break;
            }

            holder.textViewA.setText(DatesGenerator.lastTwo(current.getDate()));

            holder.textViewB.setText(current.getHours());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            Calendar rightNow = Calendar.getInstance();
            String formatted = dateFormat.format(rightNow.getTime());
            String testString = holder.textViewA.getText().toString();

            if (testString.equals(formatted)) {
                holder.textViewA.setTextColor(ContextCompat.getColor(context, R.color.today_color));
            }

        } else {
            holder.textViewA.setText(com.example.android.shiftrota.R.string.no_data);
        }

        holder.textViewA.setOnClickListener(v -> {

            tempPosition = indexInt;
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

        if (indexInt == position && indexInt != tempPosition) {
            holder.textViewA.setBackgroundColor(Color.RED);
        } else {
            Date current = mDates.get(holder.getAdapterPosition());

            int testInt = current.getStatus();

            switch (testInt) {
                case 0:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_unchecked);
                    break;
                case 1:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_will_work);
                    break;
                case 2:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_have_worked);
                    break;
                case 3:
                    holder.textViewA.setBackgroundResource(R.drawable.cell_shape_holiday);
                    break;
            }
        }
    }

    void setDates(List<Date> dates) {
        mDates = dates;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDates != null)
            return mDates.size();
        else return 0;
    }

    public interface AdapterCallback {
        void onMethodCallback(String yourValue1, String yourValue2, String yourValue3, int statusInt);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewA, textViewB;


        ViewHolder(View itemView) {
            super(itemView);
            textViewA = itemView.findViewById(R.id.item_text_view_main);
            textViewB = itemView.findViewById(R.id.item_text_view_details);
        }
    }
}
