package com.johnson.pablo.poesiaperuana.data.datasource.rest;

import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;

import java.util.List;

import io.reactivex.Observable;


/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class RestPoetsDataStore implements PoetDataStore {

    private final PoetsApiService poetsApiInterface;

    public RestPoetsDataStore() {
        poetsApiInterface = PoetsApi.get().getRetrofitService();
    }

    @Override
    public Observable<List<Poet>> loadPoets() {
        return poetsApiInterface.loadPoets();
    }

}
