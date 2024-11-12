package com.example.movieappwithdagger.di.component;

import android.app.Application;

import com.example.movieappwithdagger.MyApplication;
import com.example.movieappwithdagger.di.module.AppModule;
import com.example.movieappwithdagger.di.module.ViewModelModule;
import com.example.movieappwithdagger.di.builder.ActivityBuilderModule;
import com.example.movieappwithdagger.ui.main.favouriteMovie.FavouriteMovieFragmentBuilder;
import com.example.movieappwithdagger.ui.main.movie.MovieFragmentBuilder;
import com.example.movieappwithdagger.ui.main.movieDetails.MovieDetailsFragmentBuilder;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component( modules =
        {AndroidSupportInjectionModule.class,
                ActivityBuilderModule.class,
                MovieFragmentBuilder.class,
                MovieDetailsFragmentBuilder.class,
                FavouriteMovieFragmentBuilder.class,
                AppModule.class,
                ViewModelModule.class,


        }

)
    public interface AppComponent extends AndroidInjector<MyApplication> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

}
