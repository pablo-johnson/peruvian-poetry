package com.johnson.pablo.poesiaperuana.presentation.platform;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A Platform for Android
 * Created by pjohnson on 15/03/17.
 */
public class AndroidPlatform extends Platform {

    public static final String LABELS_FILE_NAME = "labelsFileName";
    private static final String DEFAULT_FILE = "defaultName";

    private final Context context;

    public AndroidPlatform(Context context) {
        super();
        this.context = context.getApplicationContext();
    }

    /**
     * @return the Application Context
     */
    public Context getContext() {
        return context;
    }

    /**
     * @return the default Shared Preferences file
     */
    public SharedPreferences openDefaultSharedPreferences() {
        return openSharedPreferences(DEFAULT_FILE, Context.MODE_PRIVATE);
    }

    /**
     * @param fileName name of the Shared Preferences file
     * @return an specific Shared Preferences file opened in private mode
     */
    public SharedPreferences openSharedPreferences(String fileName) {
        return openSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * @param fileName name of the Shared Preferences file.
     * @param mode     file opening mode
     * @return an specific Shared Preferences file
     */
    public SharedPreferences openSharedPreferences(String fileName, int mode) {
        return ((AndroidPlatform) Platform.get()).getContext().getSharedPreferences(fileName, mode);
    }
}
