package com.johnson.pablo.poesiaperuana.presentation.common;

import android.content.Context;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */

public interface PoetsView {

    void showProgressDialog();
    void dismissProgressDialog();
    void setTitle(String title);
    void showError(String message);
    Context getContext();
}
