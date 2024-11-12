package com.example.movieappwithdagger.ui.main.favouriteMovie;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.movieappwithdagger.data.DataRepository;
import com.example.movieappwithdagger.data.local.DatabaseRepository;
import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.ui.base.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

public class FavouriteMovieViewModel extends BaseViewModel {



    private MutableLiveData<List<Moviedata>> movies;
    private ObservableField<Boolean> dataReady;

    @Inject
    public FavouriteMovieViewModel(DataRepository DataRepository) {
        super(DataRepository);
        movies=new MutableLiveData<>();
        dataReady=new ObservableField<>(false);
    }


    public void getFavouriteMovies(Context context){

        getDataRepository().getDatabaseRepository().getFavouriteMovies(context).observeForever(new Observer<List<Moviedata>>() {
            @Override
            public void onChanged(List<Moviedata> moviedata) {
                movies.setValue(moviedata);
                 dataReady.set(true);
            }
        });
    }

    public ObservableField<Boolean> getDataReady() {
        return dataReady;
    }
    public MutableLiveData<List<Moviedata>> getListMovies() {
        return movies;
    }

}