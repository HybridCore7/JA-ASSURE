package com.example.movieappwithdagger.utils;

import android.view.View;
import android.widget.ImageView;

import com.example.movieappwithdagger.data.model.Moviedata;


public interface ItemClickListener {
    void onItemClick(View view, Moviedata movie);


}
