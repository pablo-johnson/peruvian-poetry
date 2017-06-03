package com.johnson.pablo.poesiaperuana.presentation;

import android.app.Application;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.johnson.pablo.poesiaperuana.presentation.platform.AndroidPlatform;
import com.johnson.pablo.poesiaperuana.presentation.platform.Platform;

/**
 * Created by pjohnson on 3/04/17.
 */
public class PoemApplication extends Application {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        Platform.setPlatform(new AndroidPlatform(this));
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }
}
