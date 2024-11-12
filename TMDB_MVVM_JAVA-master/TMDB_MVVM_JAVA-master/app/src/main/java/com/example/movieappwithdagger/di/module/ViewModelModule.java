package com.example.movieappwithdagger.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieappwithdagger.ui.main.favouriteMovie.FavouriteMovieViewModel;
import com.example.movieappwithdagger.ui.main.movie.MovieViewModel;
import com.example.movieappwithdagger.di.ViewModelKey;
import com.example.movieappwithdagger.ViewModelProviderFactory;
import com.example.movieappwithdagger.ui.main.movieDetails.MovieDetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel.class)
    public abstract ViewModel bindMovieViewModel(MovieViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel.class)
    public abstract ViewModel bindMovieDetailsViewModel(MovieDetailsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteMovieViewModel.class)
    public abstract ViewModel bindfavaouriteMovieViewModel(FavouriteMovieViewModel viewModel);


    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelFactory);
}
