package com.example.movieappwithdagger.ui.main.movieDetails;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieDetailsFragmentBuilder {


    @ContributesAndroidInjector(modules = MoviesDetailsModule.class)
    abstract DetailsFragment contributePostsFragment();
}
