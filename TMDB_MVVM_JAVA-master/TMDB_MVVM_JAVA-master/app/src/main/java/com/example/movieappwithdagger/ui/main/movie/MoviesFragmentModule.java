package com.example.movieappwithdagger.ui.main.movie;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesFragmentModule {

    @Provides
    MovieAdapter provideMoviesAdapter(){
        return new MovieAdapter();
    }
}
