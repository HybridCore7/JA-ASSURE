package com.example.movieappwithdagger.data.remote.client;


import com.example.movieappwithdagger.data.model.*;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebServices {
    @GET("/3/movie/{category}")
    Single<ResultsMovies> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
    @GET("/3/movie/{movie_id}")
    Single<MovieDetails> getMovieDetails(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET("/3/movie/{movie_id}/similar")
    Single<ResultsMovies> getSimilarMovies(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

}
