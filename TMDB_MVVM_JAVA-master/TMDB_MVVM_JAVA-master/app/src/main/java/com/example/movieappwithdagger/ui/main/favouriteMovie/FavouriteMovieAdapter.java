package com.example.movieappwithdagger.ui.main.favouriteMovie;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.databinding.FavouriteMovieItemBinding;
import com.example.movieappwithdagger.databinding.FilmcardBinding;
import com.example.movieappwithdagger.ui.base.BaseItemListener;
import com.example.movieappwithdagger.ui.base.BaseRecyclerViewAdapter;
import com.example.movieappwithdagger.ui.base.BaseViewHolder;
import com.example.movieappwithdagger.ui.main.movie.MovieItemViewModel;
import com.example.movieappwithdagger.utils.ItemClickListener;

import java.util.ArrayList;

public class FavouriteMovieAdapter extends BaseRecyclerViewAdapter< Moviedata > {
    MoviesAdapterListener moviesAdapterListener;

    public FavouriteMovieAdapter() {
        super(new ArrayList<>());
    }

    public void setItemAdapterListener(MoviesAdapterListener moviesAdapterListener) {
        this.moviesAdapterListener = moviesAdapterListener;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        FavouriteMovieItemBinding favouriteMovieItemBinding = FavouriteMovieItemBinding.inflate(layoutInflater, parent, false);
        return new FavouriteMoviesViewHolder(favouriteMovieItemBinding);
    }

    public void onBindViewHolder(@NonNull FavouriteMoviesViewHolder holder, int position) {
        if(getItems().size()>0){
            holder.onBindItem(position);}
    }

    public class FavouriteMoviesViewHolder  extends BaseViewHolder  implements FavouriteMovieItemViewModel.ItemViewModelListener  {

        private FavouriteMovieItemBinding itemBinding;

        public FavouriteMoviesViewHolder(@NonNull FavouriteMovieItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        @Override
        public void onBindItem(int position) {
            itemBinding.setFavouritemoveitem(new FavouriteMovieItemViewModel(getItems().get(position),this));

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
/*
    private ArrayList<Moviedata> favouriteMovies;
    ItemAdapterListener itemAdapterListener;

    public void setItemAdapterListener(ItemAdapterListener itemAdapterListener) {
        this.itemAdapterListener = itemAdapterListener;
    }

    public FavouriteMovieAdapter() {
        favouriteMovies=new ArrayList<>();
    }
    public void clearItems(){

        favouriteMovies.clear();
    }
    public void addItems(ArrayList<Moviedata> items){
        if(items!=null){
            this.favouriteMovies.addAll(items);
            notifyDataSetChanged();}

    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        FavouriteMovieItemBinding favouriteMovieItemBinding = FavouriteMovieItemBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(favouriteMovieItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        if(favouriteMovies.size()>0){
            holder.onBindItem(favouriteMovies.get(position));}
    }

    @Override
    public int getItemCount() {
        return favouriteMovies.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements FavouriteMovieItemViewModel.ItemViewModelListener  {

        FavouriteMovieItemBinding itemBinding;


        public ItemViewHolder(@NonNull FavouriteMovieItemBinding itemBinding) {
            super(itemBinding.getRoot());


            this.itemBinding=itemBinding;
        }
        public void onBindItem(Moviedata item) {
            // set Data to variable to set each specific Item
            itemBinding.setFavouritemoveitem(new FavouriteMovieItemViewModel(item,this,itemBinding.filmposter));

            itemBinding.executePendingBindings();
        }


        @Override
        public void onItemClick(Moviedata item, ImageView poster) {
            if(item!=null)
                itemAdapterListener.onItemClick(item,poster);
        }
    }
    public interface ItemAdapterListener extends ItemClickListener {}
*/