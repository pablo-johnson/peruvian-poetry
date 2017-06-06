package com.johnson.pablo.poesiaperuana.presentation.main;

import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStoreFactory;
import com.johnson.pablo.poesiaperuana.data.repository.PoetDataRepository;
import com.johnson.pablo.poesiaperuana.domain.interactors.PoetInteractor;
import com.johnson.pablo.poesiaperuana.presentation.common.PoetsPresenter;
import com.johnson.pablo.poesiaperuana.presentation.common.PoetsView;

/**
 * Created by pjohnson on 3/06/17.
 */
public class MainPresenter extends PoetsPresenter<PoetsView> {

    protected MainPresenter(PoetsView view) {
        super(view);
    }

    public void initDataBase() {
        PoetInteractor poetInteractor = new PoetInteractor(new PoetDataRepository(new PoetDataStoreFactory()));
        poetInteractor.initPoetData();
    }

}
