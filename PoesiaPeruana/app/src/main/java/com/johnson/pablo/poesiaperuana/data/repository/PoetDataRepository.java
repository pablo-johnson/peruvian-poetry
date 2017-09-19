package com.johnson.pablo.poesiaperuana.data.repository;


import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStoreFactory;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.model.Version;
import com.johnson.pablo.poesiaperuana.domain.repository.PoetRepository;
import com.johnson.pablo.poesiaperuana.presentation.platform.AndroidPlatform;
import com.johnson.pablo.poesiaperuana.presentation.platform.Platform;

import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

        final PoetDataStore dbPoetDataStore = this.poetDataStoreFactory.create(PoetDataStoreFactory.DB);
        return dbPoetDataStore.loadPoets();

//        final PoetDataStore cloudPoetDataStore = this.poetDataStoreFactory
//                .create(PoetDataStoreFactory.CLOUD);
//
//        Flowable<Version> cloudVersion = Flowable.create(new FlowableOnSubscribe<Version>() {
//            @Override
//            public void subscribe(@NonNull FlowableEmitter<Version> emitter) throws Exception {
//                if (!emitter.isCancelled()) {
//                    Flowable<Version> flowable = cloudPoetDataStore.getPoetsVersion();
//                    flowable.onErrorReturn(new Function<Throwable, Version>() {
//                        @Override
//                        public Version apply(@NonNull Throwable throwable) throws Exception {
//                            return new Version(0.0);
//                        }
//                    }).onErrorResumeNext(new Function<Throwable, Publisher<? extends Version>>() {
//                        @Override
//                        public Publisher<? extends Version> apply(@NonNull Throwable throwable) throws Exception {
//                            return dbPoetDataStore.getPoetsVersion();
//                        }
//                    });
//                    emitter.onNext(flowable.blockingFirst());
//                }
//            }
//        }, BackpressureStrategy.BUFFER);
//        cloudVersion.onErrorReturn(new Function<Throwable, Version>() {
//            @Override
//            public Version apply(@NonNull Throwable throwable) throws Exception {
//                return new Version(0.0);
//            }
//        }).onErrorResumeNext(new Function<Throwable, Publisher<? extends Version>>() {
//            @Override
//            public Publisher<? extends Version> apply(@NonNull Throwable throwable) throws Exception {
//                return dbPoetDataStore.getPoetsVersion();
//            }
//        });
//
//        Flowable<Version> dbVersion = Flowable.create(new FlowableOnSubscribe<Version>() {
//            @Override
//            public void subscribe(@NonNull final FlowableEmitter<Version> emitter) throws Exception {
//                if (!emitter.isCancelled()) {
//                    emitter.onNext(dbPoetDataStore.getPoetsVersion().onErrorReturn(new Function<Throwable, Version>() {
//                        @Override
//                        public Version apply(@NonNull Throwable throwable) throws Exception {
//                            return new Version(0.0);
//                        }
//                    }).onErrorResumeNext(new Function<Throwable, Publisher<? extends Version>>() {
//                        @Override
//                        public Publisher<? extends Version> apply(@NonNull Throwable throwable) throws Exception {
//                            return Flowable.just(new Version(0.0));
//                        }
//                    }).onErrorResumeNext(new Flowable<Version>() {
//                        @Override
//                        protected void subscribeActual(Subscriber<? super Version> s) {
//                            s.onNext(new Version(0.0));
//                        }
//                    }).blockingFirst(new Version(0.0)));
//                }
//            }
//        }, BackpressureStrategy.BUFFER).onErrorReturn(new Function<Throwable, Version>() {
//            @Override
//            public Version apply(@NonNull Throwable throwable) throws Exception {
//                return null;
//            }
//        });
//
//        return Flowable.zip(cloudVersion, dbVersion, new BiFunction<Version, Version, List<Poet>>() {
//            @Override
//            public List<Poet> apply(@NonNull Version cloudVersion, @NonNull Version dbVersion) throws Exception {
//                PoetDataStore poetDataStore;
//                boolean shouldSaveData = false;
//                if (cloudVersion.getVersion() > dbVersion.getVersion()) {
//                    dbPoetDataStore.setPoetVersion(new Version(cloudVersion.getVersion()));
//                    poetDataStore = cloudPoetDataStore;
//                    shouldSaveData = true;
//                } else {
//                    poetDataStore = dbPoetDataStore;
//                }
//                Flowable<List<Poet>> flowable = poetDataStore.loadPoets();
//                List<Poet> poets = flowable.blockingFirst();
//                if (shouldSaveData) {
//                    dbPoetDataStore.savePoets(poets);
//                }
//                return poets;
//            }
//        });
    }

    @Override
    public void loadPoetsFromNetwork() {
        final PoetDataStore dbPoetDataStore = this.poetDataStoreFactory.create(PoetDataStoreFactory.DB);
        final PoetDataStore cloudPoetDataStore = this.poetDataStoreFactory
                .create(PoetDataStoreFactory.CLOUD);
        cloudPoetDataStore.loadPoets()
                .subscribeOn(Schedulers.io())
                .delay(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<Poet>>() {
                    @Override
                    public void onSubscribe(Subscription s) {

                    }

                    @Override
                    public void onNext(List<Poet> poets) {
                        dbPoetDataStore.deleteAllPoets();
                        dbPoetDataStore.savePoets(poets);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

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
