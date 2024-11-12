package com.example.movieappwithdagger.ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter <T> extends RecyclerView.Adapter<BaseViewHolder> {


    private ArrayList<T> items;

    public BaseRecyclerViewAdapter(ArrayList<T> items){
        this.items = items;
    }

    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBindItem(position);
    }

    public void clearItems() {
        items.clear();
    }

    public void addItems(List<T> items){
        if (items != null) {
            this.items.addAll(items);
            notifyDataSetChanged();
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public ArrayList< T > getItems() {
        return items;
    }

}
