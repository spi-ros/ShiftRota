package com.example.android.shiftrota;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.UI.DateViewModel;
import com.example.android.shiftrota.data.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Date> mDates;
    private Context context;
    private int testInt;
    private DateViewModel mDateViewModel;

    DateAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        mDateViewModel = ViewModelProviders.of((MainActivity) context).get(DateViewModel.class);
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

            testInt = current.getStatus();
            switch (testInt) {
                case 0:
                    holder.textView.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.cellColor));
                    break;
                case 1:
                    holder.textView.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.will_work));
                    break;
                case 2:
                    holder.textView.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.have_worked));
                    break;
                case 3:
                    holder.textView.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.canceled));
                    break;
                case 4:
                    holder.textView.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.holiday));
                    break;
            }

            holder.textView.setText(current.getDate());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
            Calendar rightNow = Calendar.getInstance();
            String formatted = dateFormat.format(rightNow.getTime());
            String testString = holder.textView.getText().toString();

            if (testString.equals(formatted)) {
                holder.textView.setTextColor(ContextCompat.getColor(context, R.color.today_color));
            }

        } else {
            holder.textView.setText(com.example.android.shiftrota.R.string.no_data);
        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            int counter = testInt;

            @Override
            public void onClick(View v) {

                String string = holder.textView.getText().toString();
                if (counter == 0) {
                    v.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.will_work));
                    Date date = new Date(string, 1);
                    mDateViewModel.insert(date);
                    counter++;
                } else if (counter == 1) {
                    v.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.have_worked));
                    Date date = new Date(string, 2);
                    mDateViewModel.insert(date);
                    counter++;
                } else if (counter == 2) {
                    v.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.canceled));
                    Date date = new Date(string, 3);
                    mDateViewModel.insert(date);
                    counter++;
                } else if (counter == 3) {
                    v.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.holiday));
                    Date date = new Date(string, 4);
                    mDateViewModel.insert(date);
                    counter++;
                } else {
                    v.setBackgroundColor(ContextCompat.
                            getColor(context, R.color.cellColor));
                    Date date = new Date(string, 0);
                    mDateViewModel.insert(date);
                    counter = 0;
                }
                Toast.makeText(context.getApplicationContext(), "You clicked " +
                        ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
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

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_text_view);
        }
    }
}
