package com.johnson.pablo.poesiaperuana.data.datasource.db;

import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class DbPoetDataStore implements PoetDataStore {

    public DbPoetDataStore() {

    }

    @Override
    public Observable<List<Poet>> loadPoets() {
        //TODO:
        return null;
    }
}
