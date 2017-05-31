package com.johnson.pablo.poesiaperuana.presentation.common;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class PoetsFragment extends Fragment implements PoetsView {

    protected PoetsFragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentListener = (PoetsFragmentListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

    @Override
    public void showProgressDialog() {
        if (fragmentListener != null) {
            fragmentListener.showProgressDialog();
        }
    }

    @Override
    public void dismissProgressDialog() {
        if (fragmentListener != null) {
            fragmentListener.dismissProgressDialog();
        }
    }

    @Override
    public void setTitle(String title) {
        if (fragmentListener != null) {
            fragmentListener.setTitle(title);
        }
    }

    @Override
    public void showError(String message) {
        if (fragmentListener != null) {
            fragmentListener.showError(message);
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
