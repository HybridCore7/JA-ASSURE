package com.example.movieappwithdagger.ui.main.movieDetails;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.movieappwithdagger.R;
import com.example.movieappwithdagger.ViewModelProviderFactory;
import com.example.movieappwithdagger.data.model.Moviedata;
import com.example.movieappwithdagger.databinding.FragmentDetailsBinding;
import com.example.movieappwithdagger.ui.base.BaseFragment;
import com.example.movieappwithdagger.ui.main.movie.MovieAdapter;
import com.example.movieappwithdagger.ui.main.movie.MovieViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class DetailsFragment extends BaseFragment< FragmentDetailsBinding, MovieDetailsViewModel > implements MovieAdapter.MoviesAdapterListener {
    private Moviedata movie;


    @Inject
    MovieAdapter similarMoviesAdapter;
    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setActionBar();

        if(getArguments()!=null){
            DetailsFragmentArgs args=DetailsFragmentArgs.fromBundle(getArguments());
            this.movie=args.getSelectedMovie();
        }
        getViewDataBinding().collapsingToolbar.setTitleEnabled(true);
        getViewDataBinding().collapsingToolbar.setTitle(movie.getTitle());

        return getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getViewModel().getMovieDetails(Integer.valueOf(movie.getId()));

        isMovieExist();

        initRecycleView();

        getSimilarMovies(Integer.valueOf( movie.getId()),1);
        setFavouriteBtn();



    }

    @Override
    public int getBindingVariable() {
        return BR.detailsViewModel;
    }


    private void getSimilarMovies(int movieId,int page){
        getViewModel().getSimilarMovies(movieId,page);

    }



    private void isMovieExist(){
        if(getViewModel().isExist(Long.parseLong(movie.getId()),getContext()))
            getViewDataBinding().fabFavorite.setImageResource(R.drawable.ic_favorite);


    }


    public MovieDetailsViewModel setViewModel(){
        return new ViewModelProvider(this , providerFactory).get(MovieDetailsViewModel.class);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_details;
    }


    private void initRecycleView(){

        getViewDataBinding().rvSimilarMovies .setLayoutManager(new LinearLayoutManager(getContext() , RecyclerView.HORIZONTAL,false));
        getViewDataBinding().rvSimilarMovies .setHasFixedSize(true);
        similarMoviesAdapter.setListener(this);
        getViewDataBinding().rvSimilarMovies .setAdapter(similarMoviesAdapter);
    }


    public void setActionBar(){
        CollapsingToolbarLayout layout = getViewDataBinding().collapsingToolbar;
        Toolbar toolbar = getViewDataBinding().toolbar;
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(layout, toolbar, navController, appBarConfiguration);
    }




    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    public void setFavouriteBtn(){

        getViewDataBinding().fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getViewModel().isExist(Long.parseLong(movie.getId()),getContext())) {
                    getViewModel().deleteMovie(Long.parseLong(movie.getId()), getContext());
                    getViewDataBinding().fabFavorite.setImageResource(R.drawable.ic_un_favorite);

                }
                else {
                    getViewModel().saveMovie(movie, getContext());
                    getViewDataBinding().fabFavorite.setImageResource(R.drawable.ic_favorite);
                    Toast.makeText(getContext(), "Add To Your Favourite ", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @Override
    public void onItemClick(View view, Moviedata item) {
        DetailsFragmentDirections.ActionDetailsFragmentSelf actionDetailsFragmentSelf=
                DetailsFragmentDirections.actionDetailsFragmentSelf(item);
        getNavController().navigate(actionDetailsFragmentSelf);
    }
}
