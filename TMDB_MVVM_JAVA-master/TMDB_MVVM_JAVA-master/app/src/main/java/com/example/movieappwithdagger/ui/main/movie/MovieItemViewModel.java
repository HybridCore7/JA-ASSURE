package com.example.movieappwithdagger.ui.main.movie;

import android.view.View;
import android.widget.ImageView;

import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.ui.base.BaseItemListener;
import com.example.movieappwithdagger.utils.ItemClickListener;


public class MovieItemViewModel {
    private Moviedata item;

    private ItemViewModelListener itemViewModelListener;

    public MovieItemViewModel(Moviedata item, ItemViewModelListener itemViewModelListener) {
        this.item = item;
        this.itemViewModelListener = itemViewModelListener;
    }

    public void onItemClick(View view) {
        itemViewModelListener.onItemClick(view,item);
    }

    public Moviedata getItem() {
        return item;
    }
    public interface ItemViewModelListener extends BaseItemListener<Moviedata> {

    }
}
