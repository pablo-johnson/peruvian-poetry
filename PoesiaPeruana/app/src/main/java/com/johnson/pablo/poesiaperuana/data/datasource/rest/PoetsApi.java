package com.johnson.pablo.poesiaperuana.data.datasource.rest;


import com.johnson.pablo.poesiaperuana.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetsApi {

    private static PoetsApi instance;
    private PoetsApiService service;


    private PoetsApi() {
    }

    public static PoetsApi get() {
        if (instance == null) {
            instance = new PoetsApi();
        }
        return instance;
    }

    public PoetsApiService getRetrofitService() {
        if (service == null) {
            OkHttpClient client = OkHttpSingleton.getOkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
            service = retrofit.create(PoetsApiService.class);
        }
        return service;
    }

}
