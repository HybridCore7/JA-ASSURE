package com.example.movieappwithdagger.ui.main.movie;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieFragmentBuilder {

    @ContributesAndroidInjector(modules = MoviesFragmentModule.class)
    abstract MoviesFragment contributePostsFragment();
}
