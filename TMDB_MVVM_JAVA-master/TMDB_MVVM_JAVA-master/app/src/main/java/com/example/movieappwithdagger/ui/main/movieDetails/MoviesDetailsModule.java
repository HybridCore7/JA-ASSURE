package com.example.movieappwithdagger.ui.main.movieDetails;

import com.example.movieappwithdagger.ui.main.movie.MovieAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesDetailsModule {

    @Provides
    MovieAdapter provideMoviesAdapter(){
        return new MovieAdapter();
    }
}
