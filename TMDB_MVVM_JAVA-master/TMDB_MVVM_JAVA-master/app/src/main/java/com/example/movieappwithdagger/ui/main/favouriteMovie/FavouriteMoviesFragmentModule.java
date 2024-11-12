package com.example.movieappwithdagger.ui.main.favouriteMovie;

import dagger.Module;
import dagger.Provides;

@Module
public class FavouriteMoviesFragmentModule {

    @Provides
    FavouriteMovieAdapter provideMoviesAdapter(){
        return new FavouriteMovieAdapter();
    }
}
