package com.example.movieappwithdagger.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movieappwithdagger.data.model.Moviedata;

import java.util.List;

@Dao
public interface MyDoa {


    @Insert
    public void addMovie(Moviedata moviedata);

    @Query("select *from Movies")
    public LiveData<List<Moviedata>> getMovies();

    @Query("select COUNT(*) from Movies where id = :movieID")
    int isExist(long movieID);

    @Query("delete from Movies where id = :movieID")
    void delete(long movieID);


}
