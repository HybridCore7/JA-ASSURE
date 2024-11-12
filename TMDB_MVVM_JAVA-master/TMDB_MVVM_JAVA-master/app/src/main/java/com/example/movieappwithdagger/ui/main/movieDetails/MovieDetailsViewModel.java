package com.example.movieappwithdagger.ui.main.movieDetails;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappwithdagger.data.DataRepository;
import com.example.movieappwithdagger.data.local.DatabaseRepository;
import com.example.movieappwithdagger.data.model.MovieDetails;
import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.data.model.ResultsMovies;
import com.example.movieappwithdagger.data.remote.ApiRepository;
import com.example.movieappwithdagger.ui.base.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailsViewModel extends BaseViewModel {

    private MutableLiveData<MovieDetails> movieDetailsList;
    private MutableLiveData<ArrayList<Moviedata>> similarMovies;
    @Inject
    public MovieDetailsViewModel(DataRepository dataRepository) {
        super(dataRepository);

        movieDetailsList=new MutableLiveData<>();
        similarMovies=new MutableLiveData<>();

    }
    public void getSimilarMovies(int id,int page){

        getDataRepository().getApiRepository().getSimilarMovies(id,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ResultsMovies>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull ResultsMovies resultsMovies) {
                        similarMovies.setValue(resultsMovies.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
    public void getMovieDetails(int id){

        getDataRepository().getApiRepository().getMovieDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieDetails>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull MovieDetails movieDetails) {
                        movieDetailsList.setValue(movieDetails);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });


    }
    public void saveMovie(Moviedata moviedata, Context context){
        getDataRepository().getDatabaseRepository().saveFavouriteMovies(context,moviedata);

    }
    public boolean isExist(long movieID,Context context){
        return getDataRepository().getDatabaseRepository().isExist(context,movieID);
    }
    public void deleteMovie(long movieID,Context context ){

        getDataRepository().getDatabaseRepository().deleteMovie(context,movieID);
    }

    public MutableLiveData<MovieDetails> getMovieDetailsLiveData() {
        return movieDetailsList;
    }

    public MutableLiveData<ArrayList<Moviedata>> getSimilarMovies() {
        return similarMovies;
    }
}
