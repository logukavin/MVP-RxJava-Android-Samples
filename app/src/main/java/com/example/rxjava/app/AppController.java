package com.example.rxjava.app;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {
    private Context context;
    private static AppController appController;
    private AppRepo appRepo;
    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();
        gson = new GsonBuilder().serializeNulls().create();
        appController = this;
    }
    public static AppController getInstance() {
        return appController;
    }

    public AppRepo getAppRepo() {
        if (appRepo == null) {
            appRepo = getNewAppRepo();
        }
        return appRepo;
    }

    private AppRepo getNewAppRepo() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(180, TimeUnit.SECONDS)
                .readTimeout(180, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().client(httpClient)
                .baseUrl(ApiUrl.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return new AppRepo(retrofit.create(ApiInterface.class));


    }
}
