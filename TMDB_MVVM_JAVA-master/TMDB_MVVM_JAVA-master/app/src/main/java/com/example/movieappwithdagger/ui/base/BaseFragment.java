package com.example.movieappwithdagger.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment <VD extends ViewDataBinding, VM extends ViewModel> extends DaggerFragment {

    private VD mViewDataBinding;
    private VM mViewModel;
    private NavController navController;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = setViewModel();
    }
    public abstract VM setViewModel();
    public abstract int getLayoutId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId() , container, false);

        return mViewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        getViewDataBinding().setVariable(getBindingVariable(),getViewModel());
        getViewDataBinding().setLifecycleOwner(this);
        getViewDataBinding().executePendingBindings();
    }
    public abstract int getBindingVariable();

    public View getRootView() {
        return mViewDataBinding.getRoot();
    }



    public VD getViewDataBinding(){
        return mViewDataBinding;
    }

    public NavController getNavController() {
        return navController;
    }

    public VM getViewModel(){
        return mViewModel;
    }
}
