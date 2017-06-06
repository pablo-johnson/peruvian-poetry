package com.johnson.pablo.poesiaperuana.data.datasource.db;

import android.content.Context;

import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStore;
import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.PoetEntity;
import com.johnson.pablo.poesiaperuana.data.datasource.db.entities.VersionEntity;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.model.Version;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class DbPoetDataStore implements PoetDataStore {

    private final PoetDatabase mDb;

    public DbPoetDataStore(Context context) {
        mDb = PoetDatabase.getDatabase(context);
    }

    @Override
    public Flowable<List<Poet>> loadPoets() {
        return mDb.poetDao().getAll().flatMap(new Function<List<PoetEntity>, Publisher<List<Poet>>>() {
            @Override
            public Publisher<List<Poet>> apply(@NonNull List<PoetEntity> poetEntities) throws Exception {
                List<Poet> poets = new ArrayList<>();
                for (PoetEntity poetEntity : poetEntities) {
                    poets.add(poetEntity.toPoet());
                }
                return Flowable.fromArray(poets);
            }
        });
    }

    @Override
    public void savePoets(List<Poet> poets) {
        List<PoetEntity> poetEntities = new ArrayList<>();
        for (Poet poet : poets) {
            PoetEntity poetEntity = new PoetEntity(poet);
            poetEntities.add(poetEntity);
        }
        mDb.poetDao().insertAll(poetEntities);
    }

    @Override
    public Flowable<Version> getPoetsVersion() {
        return mDb.versionDao().getMaxVersion().flatMap(new Function<VersionEntity, Publisher<Version>>() {
            @Override
            public Publisher<Version> apply(@NonNull VersionEntity versionEntity) throws Exception {
                return Flowable.just(versionEntity.toVersion());
            }
        });
    }

    @Override
    public void setPoetVersion(Version version) {
        VersionEntity versionEntity = new VersionEntity(version);
        mDb.versionDao().insert(versionEntity);
    }
}
