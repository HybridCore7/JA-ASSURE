package com.example.movieappwithdagger.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.movieappwithdagger.MyApplication;
import com.example.movieappwithdagger.data.local.DatabaseRepository;
import com.example.movieappwithdagger.data.local.MyAppDatabase;
import com.example.movieappwithdagger.data.remote.client.ApiConstans;
import com.example.movieappwithdagger.data.remote.client.WebServices;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    static WebServices provideApiClient(Retrofit retrofit) {
        return retrofit.create(WebServices.class);
    }


    @Provides
    @Singleton
    static DatabaseRepository provideDatabaseRepository() {
        return new DatabaseRepository();
    }
    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(ApiConstans.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }
    @Provides
    @Singleton
    static Cache provideCache(Context context) {
        File httpCacheDirectory = new File(MyApplication.getInstance().getCacheDir(), "offline");
        return new Cache(httpCacheDirectory, 5 * 1024 * 1024);
    }
    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient(@Named("network")Interceptor interceptor,@Named("nonetwork")Interceptor interceptor2, Cache cache,HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor) // used if network off OR on
                .addNetworkInterceptor(interceptor) // only used when network is on
                .addInterceptor(interceptor2)
                .build();

    }
    @Provides
    @Singleton
    static HttpLoggingInterceptor provideHttpLoggingInterceptor(){

        return
                new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
                {
                    @Override
                    public void log (String message)
                    {
                        Log.d("ServiceGenerator", "log: http log: " + message);
                    }
                } ).setLevel( HttpLoggingInterceptor.Level.BODY);
    }


    @Provides
    @Singleton
    @Named("network")
    static Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxAge(5, TimeUnit.SECONDS)
                        .build();

                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        };
    }

    @Provides
    @Singleton
    @Named("nonetwork")
    static Interceptor provideInterceptor2() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("ServiceGenerator", "offline interceptor: called.");
                Request request = chain.request();

                // prevent caching when network is on. For that we use the "networkInterceptor"
                if (!MyApplication.hasNetwork()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }
    @Provides
    @Singleton
    static Context provideApplicationContext(Application application){
        return application;
    }




}
