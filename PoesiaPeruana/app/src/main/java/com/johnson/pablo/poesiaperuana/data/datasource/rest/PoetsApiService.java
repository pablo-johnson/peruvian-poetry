package com.johnson.pablo.poesiaperuana.data.datasource.rest;


import android.util.Log;

import com.johnson.pablo.poesiaperuana.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetsApiService {

    private static PoetsApiService instance;
    private PoetsApi service;


    private PoetsApiService() {
    }

    public static PoetsApiService get() {
        if (instance == null) {
            instance = new PoetsApiService();
        }
        return instance;
    }

    public PoetsApi getRetrofitService() {
        if (service == null) {
            Log.e("Pablo", "create new retrofit client");
            OkHttpClient client = OkHttpSingleton.getOkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(PoetsApi.class);
        }
        return service;
    }

}
