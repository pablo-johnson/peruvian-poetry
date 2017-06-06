package com.johnson.pablo.poesiaperuana.data.repository;


import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStoreFactory;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.model.Version;
import com.johnson.pablo.poesiaperuana.domain.repository.PoetRepository;
import com.johnson.pablo.poesiaperuana.presentation.platform.AndroidPlatform;
import com.johnson.pablo.poesiaperuana.presentation.platform.Platform;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetDataRepository implements PoetRepository {

    private final PoetDataStoreFactory poetDataStoreFactory;

    public PoetDataRepository(PoetDataStoreFactory poetDataStoreFactory) {
        this.poetDataStoreFactory = poetDataStoreFactory;
    }

    @Override
    public Flowable<List<Poet>> loadPoets() {

        final PoetDataStore cloudPoetDataStore = this.poetDataStoreFactory
                .create(PoetDataStoreFactory.CLOUD);

        Flowable<Version> cloudVersion = Flowable.create(new FlowableOnSubscribe<Version>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Version> emitter) throws Exception {
                emitter.onNext(cloudPoetDataStore.getPoetsVersion().blockingFirst());
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        final PoetDataStore dbPoetDataStore = this.poetDataStoreFactory
                .create(PoetDataStoreFactory.DB);
        Flowable<Version> dbVersion = Flowable.create(new FlowableOnSubscribe<Version>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<Version> emitter) throws Exception {
                emitter.onNext(dbPoetDataStore.getPoetsVersion().blockingFirst());
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        return Flowable.zip(cloudVersion, dbVersion, new BiFunction<Version, Version, Integer>() {
            @Override
            public Integer apply(@NonNull Version cloudVersion, @NonNull Version dbVersion) throws Exception {
                if (cloudVersion.getVersion() > dbVersion.getVersion()) {
                    dbPoetDataStore.setPoetVersion(new Version(cloudVersion.getVersion()));
                    return PoetDataStoreFactory.CLOUD;
                } else {
                    return PoetDataStoreFactory.DB;
                }
            }
        }).flatMap(new Function<Integer, Publisher<List<Poet>>>() {
            @Override
            public Publisher<List<Poet>> apply(@NonNull Integer storeFactory) throws Exception {
                final PoetDataStore poetDataStore = poetDataStoreFactory
                        .create(storeFactory);
                Flowable<List<Poet>> flowable = poetDataStore.loadPoets();
                if (storeFactory == PoetDataStoreFactory.CLOUD) {
                    dbPoetDataStore.savePoets(flowable.blockingFirst());
                }
                return flowable;
            }
        });
    }

    @Override
    public void initPoetData() {
        if (!((AndroidPlatform) Platform.get()).openDefaultSharedPreferences()
                .getBoolean("PoetDataInitialized", false)) {
            final PoetDataStore dbPoetDataStore = this.poetDataStoreFactory
                    .create(PoetDataStoreFactory.DB);
            dbPoetDataStore.setPoetVersion(new Version(0.0));
            ((AndroidPlatform) Platform.get()).openDefaultSharedPreferences().edit()
                    .putBoolean("PoetDataInitialized", true).apply();
        }
    }
}
