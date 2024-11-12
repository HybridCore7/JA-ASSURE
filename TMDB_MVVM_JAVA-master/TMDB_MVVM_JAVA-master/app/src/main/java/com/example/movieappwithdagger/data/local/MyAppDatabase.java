package com.example.movieappwithdagger.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.movieappwithdagger.data.local.dao.MyDoa;
import com.example.movieappwithdagger.data.model.Moviedata;

@Database(entities = {Moviedata.class},version = 1, exportSchema = false)

public abstract class MyAppDatabase extends RoomDatabase {

    public abstract MyDoa myDoa();
}