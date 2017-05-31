package com.johnson.pablo.mylibrary;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by pjohnson on 4/04/17.
 */

public class RegistrationIntentService extends IntentService {
    private static final String TAG = "Pablo";


    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCM Registration Token: " + token);
    }
}
