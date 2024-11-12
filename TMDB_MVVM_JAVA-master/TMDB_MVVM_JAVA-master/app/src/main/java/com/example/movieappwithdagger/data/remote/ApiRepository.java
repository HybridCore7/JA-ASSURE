package com.example.movieappwithdagger.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.movieappwithdagger.data.model.MovieDetails;
import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.data.model.ResultsMovies;
import com.example.movieappwithdagger.data.remote.client.ApiConstans;
import com.example.movieappwithdagger.data.remote.client.WebServices;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
@Singleton
public class ApiRepository {

    private WebServices apiService;
    @Inject
    public ApiRepository(WebServices apiService){
        this.apiService = apiService;
    }
    public Single<ResultsMovies> getMovies(int page) {

        return  apiService.getMovies(
                        ApiConstans.nameCatogry,
                        ApiConstans.APIKEY,ApiConstans.LANGUAGE,page);

    }
    public Single<ResultsMovies> getSimilarMovies( int id , int page) {

        return  apiService.getSimilarMovies(
                id,
                ApiConstans.APIKEY,ApiConstans.LANGUAGE,page);

    }


    public  Single<MovieDetails> getMovieDetails(int id){

        return apiService.getMovieDetails(id,ApiConstans.APIKEY,ApiConstans.LANGUAGE);

    }


}
