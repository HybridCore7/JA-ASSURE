package com.example.movieappwithdagger.utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieappwithdagger.R;
import com.example.movieappwithdagger.data.model.Genres;
import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.data.remote.client.ApiConstans;
import com.example.movieappwithdagger.ui.main.favouriteMovie.FavouriteMovieAdapter;
import com.example.movieappwithdagger.ui.main.movie.MovieAdapter;


import java.util.ArrayList;
import java.util.List;

public class BindingUtils {

    @BindingAdapter({"adapter"})
    public static void setRecyclerViewData(RecyclerView recyclerView, ArrayList<Moviedata> items) {
        MovieAdapter movieAdapter= (MovieAdapter)recyclerView.getAdapter();
        if(movieAdapter!=null&&items!=null){
            movieAdapter.addItems(items);

        }

    }
    @BindingAdapter({"favouriteadapter"})
    public static void favouritSetRecyclerViewData(RecyclerView recyclerView, List<Moviedata> items) {
        FavouriteMovieAdapter movieAdapter= (FavouriteMovieAdapter)recyclerView.getAdapter();
        if(movieAdapter!=null&&items!=null){
            movieAdapter.clearItems();

            movieAdapter.addItems(new ArrayList<>(items));

        }

    }
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(ApiConstans.IMAGEBASE_URL+url)
                .into(imageView);
    }

    @BindingAdapter("setMovieCategory")
    public static void setMovieCategoty(TextView textView, List<Genres> genres){

        if(genres!=null)
        for(int i=0;i<genres.size();i++){
            if(textView.getText()!=""){
                textView.setText(textView.getText()+","+genres.get(i).getName());}
            else{
                textView.setText(genres.get(i).getName());}

        }
    }

    @BindingAdapter("setMovieStatueImage")
    public static void setMovieStatueImage(ImageView image,String IsReleased){
        if(IsReleased!=null){
        if(IsReleased.equals("Released")){image.setImageResource(R.drawable.ic_released);}
        else{image.setImageResource(R.drawable.ic_un_released);}}


    }

   @BindingAdapter("setRate")
    public static void setRate(RatingBar ratingBar,double rate){

        ratingBar.setRating((float) rate/2);


    }
    @BindingAdapter("setInteger")
    public static void setInteger(TextView textView ,int number){
        textView.setText(String.valueOf(number));

    }

    @BindingAdapter("setAdultText")
    public static void setAdultText(TextView textView ,boolean check){
        if(check){textView.setText("Yes");} else{textView.setText("No");}


    }

    @BindingAdapter("setProgressBar")
    public static void setProgressBar(ProgressBar progressBar , boolean check){
        if(check){progressBar.setVisibility(View.GONE);
        }




    }





}
