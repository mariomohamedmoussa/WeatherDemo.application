package com.example.mouss.weatherappdemo.retrofit;

import com.example.mouss.weatherappdemo.utils.Collection;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitResponse {
    private static Retrofit retrofitInstance = null;

    public static Retrofit getInstance(){
        if(retrofitInstance == null){
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(Collection.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofitInstance;
    }
}
