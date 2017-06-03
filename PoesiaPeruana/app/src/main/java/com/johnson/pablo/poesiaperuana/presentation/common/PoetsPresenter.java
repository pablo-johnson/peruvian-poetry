package com.johnson.pablo.poesiaperuana.presentation.common;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.NonNull;

/**
 * @author Pablo Johnsonn (pablo.88j@gmail.com)
 */
public abstract class PoetsPresenter<T extends PoetsView> {

    protected T view;

    protected PoetsPresenter(T view) {
        this.view = view;
    }

    protected String getString(int resId) {
        return view.getContext().getString(resId);
    }

    protected void showError(int resId) {
        showError(getString(resId));
    }

    protected void showError(String message) {
        view.showError(message);
    }

    protected abstract class PresenterSubscriber<K> implements FlowableSubscriber<K> {

        @Override
        public void onSubscribe(@NonNull Subscription s) {
            s.request(1);
        }

        @Override
        public void onNext(K response) {
            view.dismissProgressDialog();
            onSuccess(response);
        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }

        public abstract void onSuccess(K response);

        public abstract void onError(Error error);
    }

}
