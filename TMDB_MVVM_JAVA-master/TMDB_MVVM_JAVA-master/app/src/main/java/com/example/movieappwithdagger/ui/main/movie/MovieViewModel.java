package com.example.movieappwithdagger.ui.main.movie;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappwithdagger.data.DataRepository;
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

public class MovieViewModel extends BaseViewModel {

    int page2=0;
    private ObservableField<Boolean> dataReady;
    private MutableLiveData<ArrayList<Moviedata>> movies;

    @Inject
    public MovieViewModel(DataRepository dataRepository) {

        super(dataRepository);
        movies=new MutableLiveData<>();
        dataReady=new ObservableField<>(false);

    }

    public void getMovies(int page){
        if(page2!=page){

            page2=page;
            getDataRepository().getApiRepository().getMovies(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<ResultsMovies>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull ResultsMovies resultsMovies) {
                            movies.setValue(resultsMovies.getResults());
                            dataReady.set(true);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });

        }
        else{

            movies.setValue(new ArrayList<>());

        }

    }
    public ObservableField<Boolean> getDataReady() {
        return dataReady;
    }

    public MutableLiveData<ArrayList<Moviedata>> getListMovies() {
        return movies;
    }
}
