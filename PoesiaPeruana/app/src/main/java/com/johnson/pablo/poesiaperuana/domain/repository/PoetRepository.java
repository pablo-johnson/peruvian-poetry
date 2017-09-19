package com.johnson.pablo.poesiaperuana.domain.repository;


import com.johnson.pablo.poesiaperuana.domain.model.Poet;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public interface PoetRepository {

    Flowable<List<Poet>> loadPoets();

    void loadPoetsFromNetwork();

    void initPoetData();
}
