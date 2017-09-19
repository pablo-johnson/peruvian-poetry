package com.johnson.pablo.poesiaperuana.data.datasource;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.model.Version;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public interface PoetDataStore {

    Flowable<List<Poet>> loadPoets();

    void savePoets(List<Poet> poets);

    Flowable<Version> getPoetsVersion();

    void setPoetVersion(Version version);

    Flowable<List<Poet>> loadFavoritePoets();

    void saveFavoritePoet(Poet poet);

    void eraseFavoritePoet(Poet poet);

    void deleteAllPoets();
}
