package com.example.movieappwithdagger.data;

import com.example.movieappwithdagger.data.local.DatabaseRepository;
import com.example.movieappwithdagger.data.remote.ApiRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataRepository {

    private ApiRepository apiRepository;
    private DatabaseRepository databaseRepository;

    @Inject
    public DataRepository(ApiRepository apiRepository , DatabaseRepository databaseRepository){
        this.apiRepository = apiRepository;
        this.databaseRepository = databaseRepository;
    }

    public ApiRepository getApiRepository() {
        return apiRepository;
    }

    public DatabaseRepository getDatabaseRepository() {
        return databaseRepository;
    }
}
