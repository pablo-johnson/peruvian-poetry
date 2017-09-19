package com.johnson.pablo.poesiaperuana.domain.interactors;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.repository.PoetRepository;

import java.util.List;

import io.reactivex.Flowable;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetInteractor {
    private final PoetRepository poetRepository;

    public PoetInteractor(PoetRepository poetRepository) {
        this.poetRepository = poetRepository;
    }

    public Flowable<List<Poet>> loadPoets() {
        return poetRepository.loadPoets();
    }

    public void initPoetData() {
        poetRepository.initPoetData();
    }

    public void loadPoetsFromNetwork() {
        poetRepository.loadPoetsFromNetwork();
    }
}
