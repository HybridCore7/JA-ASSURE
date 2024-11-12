package com.example.movieappwithdagger.ui.main.favouriteMovie;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;

import com.example.movieappwithdagger.BR;
import com.example.movieappwithdagger.R;
import com.example.movieappwithdagger.ViewModelProviderFactory;
import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.data.remote.client.ApiConstans;
import com.example.movieappwithdagger.databinding.FragmentFavouriteMovieBinding;
import com.example.movieappwithdagger.databinding.FragmentMoviesBinding;
import com.example.movieappwithdagger.ui.base.BaseFragment;
import com.example.movieappwithdagger.ui.main.movie.MovieViewModel;
import com.example.movieappwithdagger.ui.main.movie.MoviesFragmentArgs;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class FavouriteMovieFragment extends BaseFragment<FragmentFavouriteMovieBinding, FavouriteMovieViewModel> implements FavouriteMovieAdapter.MoviesAdapterListener {


    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
    FavouriteMovieAdapter favouriteMovieAdapter;

    private LayoutAnimationController controller=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMovies();
        favouriteMovieAdapter.setItemAdapterListener(this);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeRecycleView();
    }

    @Override
    public int getBindingVariable() {
         return BR.favouriteviewModel;
    }

    public FavouriteMovieViewModel setViewModel(){
        return new ViewModelProvider(this , providerFactory).get(FavouriteMovieViewModel.class);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_favourite_movie;
    }

    private void initializeRecycleView(){

        getViewDataBinding().moviesRecycleView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        getViewDataBinding().moviesRecycleView.setHasFixedSize(true);
        animateRecycleView();
        getViewDataBinding().moviesRecycleView.setAdapter(favouriteMovieAdapter);
    }
    private void animateRecycleView(){

        controller= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_slide_bottom);
        getViewDataBinding().moviesRecycleView.setLayoutAnimation(controller);
        getViewDataBinding().moviesRecycleView.scheduleLayoutAnimation();
    }
    private void getMovies(){



        getViewModel().getFavouriteMovies(getContext());

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onItemClick(View view, Moviedata item) {

        FavouriteMovieFragmentDirections.ActionFavouriteMovieFragmentToDetailsFragment action=
                FavouriteMovieFragmentDirections.actionFavouriteMovieFragmentToDetailsFragment(item);
        getNavController().navigate(action);
    }
}