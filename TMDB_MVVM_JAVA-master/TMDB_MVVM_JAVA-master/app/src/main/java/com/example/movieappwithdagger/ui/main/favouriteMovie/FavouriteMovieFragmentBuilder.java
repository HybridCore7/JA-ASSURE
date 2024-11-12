package com.example.movieappwithdagger.ui.main.favouriteMovie;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FavouriteMovieFragmentBuilder {

    @ContributesAndroidInjector(modules = FavouriteMoviesFragmentModule.class)
    abstract FavouriteMovieFragment contributePostsFragment();
}
