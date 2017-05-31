package com.johnson.pablo.poesiaperuana.data.datasource;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public interface PoetDataStore {

    Observable<List<Poet>> loadPoets();
}
