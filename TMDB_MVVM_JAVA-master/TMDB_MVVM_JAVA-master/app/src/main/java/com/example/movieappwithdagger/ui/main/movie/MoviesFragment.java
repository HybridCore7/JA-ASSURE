package com.example.movieappwithdagger.ui.main.movie;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.movieappwithdagger.databinding.FragmentMoviesBinding;
import com.example.movieappwithdagger.ui.base.BaseFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class MoviesFragment extends BaseFragment< FragmentMoviesBinding,MovieViewModel> implements MovieAdapter.MoviesAdapterListener {


    @Inject
    ViewModelProviderFactory providerFactory;
    @Inject
     MovieAdapter movieAdapter;
    private LayoutAnimationController controller=null;
    private int page=1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            MoviesFragmentArgs args = MoviesFragmentArgs.fromBundle(getArguments());
            ApiConstans.nameCatogry = args.getCategoryType();
            getViewModel().getMovies(page);
        }

        movieAdapter.setListener(this);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeRecycleView();
        setupRecycleViewEnd();

    }




    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    public MovieViewModel  setViewModel(){
        return new ViewModelProvider(this , providerFactory).get(MovieViewModel.class);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movies;
    }

    private void initializeRecycleView(){

        getViewDataBinding().moviesRecycleView.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(),2));
        getViewDataBinding().moviesRecycleView.setHasFixedSize(true);

        animateRecycleView();
        getViewDataBinding().moviesRecycleView.setAdapter(movieAdapter);
    }

    private void getMoviesCall(){

        getViewModel().getMovies(page);

    }
    private void animateRecycleView(){
        controller= AnimationUtils.loadLayoutAnimation(getContext(),R.anim.layout_animation_slide_bottom);
        getViewDataBinding().moviesRecycleView.setLayoutAnimation(controller);
        getViewDataBinding().moviesRecycleView.scheduleLayoutAnimation();
    }

    private void setupRecycleViewEnd() {

        getViewDataBinding().moviesRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                {
                    if ((getViewDataBinding().moviesRecycleView.getAdapter().getItemCount()- 4) == ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition()){
                        page++;
                        getMoviesCall();
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }
                    }

                }}});
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }



    @Override
    public void onItemClick(View view, Moviedata item) {
        MoviesFragmentDirections.ActionMoviesFragmentToMovieDetailsFragment action=
                MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(item);
        getNavController().navigate(action);
    }
}
