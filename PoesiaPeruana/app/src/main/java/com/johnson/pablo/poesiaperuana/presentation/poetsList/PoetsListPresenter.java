package com.johnson.pablo.poesiaperuana.presentation.poetsList;

import com.johnson.pablo.poesiaperuana.data.datasource.PoetDataStoreFactory;
import com.johnson.pablo.poesiaperuana.data.repository.PoetDataRepository;
import com.johnson.pablo.poesiaperuana.domain.interactors.PoetInteractor;
import com.johnson.pablo.poesiaperuana.domain.model.Error;
import com.johnson.pablo.poesiaperuana.domain.model.Poet;
import com.johnson.pablo.poesiaperuana.presentation.common.PoetsPresenter;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pjohnson on 9/04/17.
 */
public class PoetsListPresenter extends PoetsPresenter<PoetsListView> {

    private final PoetInteractor poetInteractor;

    protected PoetsListPresenter(PoetsListView view) {
        super(view);
        poetInteractor = new PoetInteractor(new PoetDataRepository(new PoetDataStoreFactory()));
    }


    public void populatePoetsList() {
        view.showProgressDialog();
        Flowable<List<Poet>> observable = poetInteractor.loadPoets();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new PoetSubscriber<List<Poet>>() {
                    @Override
                    public void onSuccess(List<Poet> poets) {
                        view.dismissProgressDialog();
                        view.populatePoetsList(poets);
                    }

                    @Override
                    public void onError(Error error) {
                        view.dismissProgressDialog();
                        view.showError(error.getErrorMessage());
                    }
                });
    }
}
