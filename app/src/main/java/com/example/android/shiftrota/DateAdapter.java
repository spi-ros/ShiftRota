package com.example.android.shiftrota;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.shiftrota.data.Date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private Context context;
    private List<Date> mDates;


//    DateAdapter(Context context, List<String> dates) {
//        this.context = context;
//        this.dates = dates;
//    }

    @NonNull
    @Override
    public DateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final DateAdapter.ViewHolder holder, final int position) {

        if (mDates != null) {
            Date current = mDates.get(position);
            holder.textView.setText(current.getDate());
        } else {
            holder.textView.setText(com.example.android.shiftrota.R.string.no_data);
        }
//        holder.textView.setText(dates.get(position));
//        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
//        Calendar rightNow = Calendar.getInstance();
//        String formatted = format1.format(rightNow.getTime());
//        String testString = holder.textView.getText().toString();
//        if (testString.equals(formatted)) {
//            holder.textView.setTextColor(Color.RED);
//        }
    }

    void setDates(List<Date> dates){
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

//    private List<String> dates;
//    private Context context;
//    private ItemClickListener mClickListener;
//
//    // data is passed into the constructor
//    DateAdapter(Context context, List<String> dates) {
//        this.context = context;
//        this.dates = dates;
//    }
//
//    // inflates the cell layout from xml when needed
//    @Override
//    @NonNull
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
//        return new ViewHolder(v);
//    }
//
//    // binds the data to the TextView in each cell
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.textView.setText(dates.get(position));
//    }
//
//    // total number of cells
//    @Override
//    public int getItemCount() {
//        return dates.size();
//    }
//
//
//    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView textView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.item_text_view);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return dates.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
}
