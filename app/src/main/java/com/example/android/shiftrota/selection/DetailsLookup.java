package com.example.android.shiftrota.selection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shiftrota.DateAdapter;

public class DetailsLookup extends ItemDetailsLookup<Long> {

    private final RecyclerView recyclerView;

    public DetailsLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof DateAdapter.ViewHolder) {
                return ((DateAdapter.ViewHolder) viewHolder).getItemDetails();
            }
        }
        return null;
    }
}
