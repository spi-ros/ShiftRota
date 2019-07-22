package com.example.android.shiftrota;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.shiftrota.data.Date;
import com.example.android.shiftrota.data.DatesGenerator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    private List<Date> mDates;
    private Context context;
    private AdapterCallback mAdapterCallback;
    private Activity activity;
    private int indexInt = -1;
    private int tempPosition = -1;
    private int saveInt;


    DateAdapter(Context context, Activity activity, int saveInt) {
        try {
            this.mAdapterCallback = ((AdapterCallback) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AdapterCallback.");
        }
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.activity = activity;
        this.saveInt = saveInt;
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

            holder.textViewA.setText(DatesGenerator.midTwo(current.getDate()));

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

        final ActionMode[] currentActionMode = new ActionMode[1];

        ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.action_text_view, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_clear:
                        return true;
                    case R.id.menu_will_work:
                        return true;
                    case R.id.menu_have_worked:
                        return true;
                    case R.id.menu_holiday:
                        return true;
                    case R.id.menu_select_hours:
                        return true;
                    case R.id.menu_notes:
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                currentActionMode[0] = null;
            }
        };

        holder.textViewA.setOnLongClickListener(v -> {
            if (currentActionMode[0] != null) {
                return false;
            }
            currentActionMode[0] = activity.startActionMode(actionModeCallback);
            v.setSelected(true);
            return true;
        });

        holder.textViewA.setOnClickListener(v -> {

            tempPosition = indexInt;
            indexInt = position;
            notifyDataSetChanged();

            Date current = mDates.get(position);
            String dateString = current.getDate();
            String hoursString = current.getHours();
            String notesString = current.getNotes();
            int statusInt = current.getStatus();
            mAdapterCallback.onMethodCallback(dateString, hoursString, notesString, statusInt);
            Toast.makeText(context.getApplicationContext(), "You clicked " +
                    ((TextView) v).getText().toString(), Toast.LENGTH_SHORT).show();
        });

        if (indexInt == position && indexInt != tempPosition && saveInt == 0) {
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

        TextView textViewA;
//        LinearLayout myView;
//        LinearLayout linearLayout;
//        TextView textViewB;


        ViewHolder(View itemView) {
            super(itemView);
            textViewA = itemView.findViewById(R.id.item_text_view_a);
            // previously invisible view
//            myView = itemView.findViewById(R.id.my_view);
//            linearLayout = itemView.findViewById(R.id.hidden_layout);
//            textViewB = itemView.findViewById(R.id.item_text_view_b);
        }
    }
}
