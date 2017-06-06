package com.johnson.pablo.poesiaperuana.data.datasource.rest;

import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.model.Version;

import java.util.List;

import io.reactivex.Flowable;


/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class RestPoetsDataStore implements PoetDataStore {

    private final PoetsApi poetsApiInterface;

    public RestPoetsDataStore() {
        poetsApiInterface = PoetsApiService.get().getRetrofitService();
    }

    @Override
    public Flowable<List<Poet>> loadPoets() {
        return poetsApiInterface.loadPoets();
    }

    @Override
    public void savePoets(List<Poet> poets) {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

    @Override
    public Flowable<Version> getPoetsVersion() {
        return poetsApiInterface.getPoetsVersion();
    }

    @Override
    public void setPoetVersion(Version version) {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

}
