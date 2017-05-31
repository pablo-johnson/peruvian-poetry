package com.johnson.pablo.poesiaperuana.presentation;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by pjohnson on 3/04/17.
 */
public class PoemApplication extends Application {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
}
