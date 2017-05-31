package com.johnson.pablo.poesiaperuana.presentation.utils.media.loaders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.johnson.pablo.poesiaperuana.presentation.utils.media.transformations.glide.BorderedCircleGlideTransformation;
import com.johnson.pablo.poesiaperuana.presentation.utils.media.transformations.glide.CircleGlideTransformation;

import java.io.File;

/**
 * @author Pablo Johnson
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void loadFromUrl(String url, ImageView imageView, Drawable placeholder) {
        Glide.with(imageView.getContext()).load(url).placeholder(placeholder).into(imageView);
    }

    @Override
    public void loadFromUrl(String url, ImageView imageView, ImageTransformation transformation, Drawable placeholder) {
        loadFromUrl(url, imageView, transformation, null, placeholder);
    }

    @Override
    public void loadFromUrl(String url, ImageView imageView, ImageTransformation transformation, Callback callback, Drawable placeholder) {
        Context context = imageView.getContext();
        load(context, Glide.with(context).load(url), transformation, callback, placeholder).into(imageView);
    }

    @Override
    public void loadFromFile(File file, ImageView imageView, ImageTransformation transformation) {
        Context context = imageView.getContext();
        load(context, Glide.with(context).load(file), transformation, null, null).into(imageView);
    }

    private <T> DrawableTypeRequest<T> load(
            Context context,
            DrawableTypeRequest<T> load,
            ImageTransformation transformation,
            final Callback callback,
            Drawable placeholder) {
        load.centerCrop();
        if (placeholder != null) {
            load.placeholder(placeholder);
        }
        if (context != null && transformation != null) {
            switch (transformation) {
                case BORDERED_CIRCLE:
                    load.transform(new BorderedCircleGlideTransformation(context));
                    break;
                case CIRCLE:
                    load.transform(new CircleGlideTransformation(context));
                    break;
            }
        }
        if (callback != null) {
            load.listener(new RequestListener<T, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, T model, Target<GlideDrawable> target, boolean isFirstResource) {
                    callback.onFailure();
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, T model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    callback.onSuccess();
                    return false;
                }
            });
        }
        return load;
    }
}