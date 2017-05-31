package com.johnson.pablo.poesiaperuana.presentation.utils.media.loaders;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.File;

/**
 * @author Pablo Johnson (pablo.88j@gmail.com)
 */
public interface ImageLoader {

    interface Callback {
        void onSuccess();

        void onFailure();
    }

    enum ImageTransformation {
        BORDERED_CIRCLE,
        CIRCLE
    }

    void loadFromUrl(String url, ImageView imageView, Drawable placeholder);

    void loadFromUrl(String url, ImageView imageView, ImageTransformation transformation, Drawable placeholder);

    void loadFromUrl(String url, ImageView imageView, ImageTransformation transformation, ImageLoader.Callback callback, Drawable placeholder);

    void loadFromFile(File file, ImageView imageView, ImageTransformation transformation);
}