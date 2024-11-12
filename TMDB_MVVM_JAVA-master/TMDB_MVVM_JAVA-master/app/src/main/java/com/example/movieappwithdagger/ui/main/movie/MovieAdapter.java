package com.example.movieappwithdagger.ui.main.movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.databinding.FilmcardBinding;
import com.example.movieappwithdagger.ui.base.BaseItemListener;
import com.example.movieappwithdagger.ui.base.BaseRecyclerViewAdapter;
import com.example.movieappwithdagger.ui.base.BaseViewHolder;
import com.example.movieappwithdagger.utils.ItemClickListener;

import java.util.ArrayList;

public class MovieAdapter extends BaseRecyclerViewAdapter< Moviedata > {

    private MoviesAdapterListener moviesAdapterListener;

    public MovieAdapter() {
        super(new ArrayList<>());
    }

    public void setListener(MoviesAdapterListener moviesAdapterListener) {
        this.moviesAdapterListener = moviesAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(getItemCount()>0){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            FilmcardBinding filmcardBinding = FilmcardBinding.inflate(layoutInflater, parent, false);
            return new MoviesViewHolder(filmcardBinding);}
        else{

            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(getItems().size()>0){
            holder.onBindItem(position);}
    }


    public class MoviesViewHolder  extends BaseViewHolder  implements MovieItemViewModel.ItemViewModelListener  {

        private  FilmcardBinding itemBinding;

        public MoviesViewHolder(@NonNull FilmcardBinding itemBinding) {
            super(itemBinding.getRoot());


            this.itemBinding=itemBinding;
        }


        @Override
        public void onBindItem(int position) {
            itemBinding.setMoveitem(new MovieItemViewModel(getItems().get(position),this));

            itemBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(View view, Moviedata item) {
            if(item!=null)
                moviesAdapterListener.onItemClick(view,item);
        }
    }
    public interface MoviesAdapterListener extends BaseItemListener< Moviedata > {

    }
}
