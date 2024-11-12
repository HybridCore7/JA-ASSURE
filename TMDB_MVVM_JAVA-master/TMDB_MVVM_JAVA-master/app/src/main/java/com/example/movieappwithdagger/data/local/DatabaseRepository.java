package com.example.movieappwithdagger.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.movieappwithdagger.data.model.Moviedata;

import java.util.ArrayList;
import java.util.List;

public class DatabaseRepository {
    private MyAppDatabase myAppDatabase;
    private MutableLiveData<ArrayList<Moviedata>> storedMovies;

    public MutableLiveData<ArrayList<Moviedata>> getStoredMovies() {
        return storedMovies;
    }

    public DatabaseRepository() {
        storedMovies=new MutableLiveData<>();
    }

    public LiveData<List<Moviedata>> getFavouriteMovies(Context context){
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        return myAppDatabase.myDoa().getMovies();


    }
    public void saveFavouriteMovies(Context context,Moviedata moviedata){
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        myAppDatabase.myDoa().addMovie(moviedata);
    }

    public boolean isExist(Context context,long movieID){
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();

        if(myAppDatabase.myDoa().isExist(movieID)==1){
            return true;
        }
        return false;

    }
    public void deleteMovie(Context context,long movieID){
        myAppDatabase= Room.databaseBuilder(context,MyAppDatabase.class,"Moviedb").allowMainThreadQueries().build();
        myAppDatabase.myDoa().delete(movieID);

    }



}
