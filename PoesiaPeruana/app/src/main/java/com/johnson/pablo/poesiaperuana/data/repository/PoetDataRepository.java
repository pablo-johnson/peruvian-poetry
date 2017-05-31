package com.johnson.pablo.poesiaperuana.data.repository;


import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStoreFactory;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.repository.PoetRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetDataRepository implements PoetRepository {

    private final PoetDataStoreFactory poetDataStoreFactory;

    public PoetDataRepository(PoetDataStoreFactory poetDataStoreFactory) {
        this.poetDataStoreFactory = poetDataStoreFactory;
    }

    @Override
    public Observable<List<Poet>> loadPoets() {
        final PoetDataStore poetDataStore = this.poetDataStoreFactory
                .create(PoetDataStoreFactory.CLOUD);
        return poetDataStore.loadPoets();
    }
}
