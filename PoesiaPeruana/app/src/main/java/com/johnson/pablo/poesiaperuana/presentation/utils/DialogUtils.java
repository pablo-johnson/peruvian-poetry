package com.johnson.pablo.poesiaperuana.presentation.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.johnson.pablo.poesiaperuana.R;


/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */

public final class DialogUtils {

    private DialogUtils() {
    }

    public static AlertDialog createErrorDialog(Activity activity, String message) {
        return createSimpleDialog(activity, activity.getString(R.string.dialog_title_error), message);
    }

    public static AlertDialog createSimpleDialog(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .create();
    }

    public static ProgressDialog createProgressDialog(Context context, String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}
