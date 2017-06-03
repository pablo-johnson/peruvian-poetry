package com.johnson.pablo.poesiaperuana.domain.interactors;

import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.domain.repository.PoetRepository;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

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
}
