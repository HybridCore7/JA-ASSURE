package com.example.movieappwithdagger.ui.base;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieappwithdagger.data.DataRepository;

public abstract class BaseViewModel extends ViewModel {

    private DataRepository dataRepository;

    public BaseViewModel(DataRepository dataRepository){
        this.dataRepository = dataRepository;
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }
}
