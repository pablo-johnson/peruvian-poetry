package com.johnson.pablo.poesiaperuana.data.datasource.rest;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public class OkHttpSingleton {
    private static OkHttpClient okHttpClient;

    private OkHttpSingleton() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .cache(createCacheForOkHTTP())
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }

    private static Cache createCacheForOkHTTP() {
        return new Cache(getDirectory(), 1024 * 1024 * 10);
    }

    public static File getDirectory() {
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "TP" + File.separator);
        root.mkdirs();
        final String fName = "TP.ok";
        return new File(root, fName);
    }
}
