package com.example.movieappwithdagger.di.builder;

import com.example.movieappwithdagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity conrtributeMainActivity();


}
