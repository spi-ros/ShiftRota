package com.example.android.shiftrota.selection;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.shiftrota.DateAdapter;

public class MyItemDetailsLookup extends ItemDetailsLookup<Long> {

    private final RecyclerView mRecyclerView;

    public MyItemDetailsLookup(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            return ((DateAdapter.ViewHolder) mRecyclerView.getChildViewHolder(view)).getItemDetails();
        }
        return null;
    }
}
