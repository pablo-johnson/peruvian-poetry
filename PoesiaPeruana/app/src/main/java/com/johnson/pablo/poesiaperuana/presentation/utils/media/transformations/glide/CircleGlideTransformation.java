package com.johnson.pablo.poesiaperuana.presentation.utils.media.transformations.glide;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.johnson.pablo.poesiaperuana.presentation.utils.media.transformations.PoetTransformationUtils;


/**
 * Pablo Johnson (pablo.88j@gmail.com)
 */
public class CircleGlideTransformation extends BitmapTransformation {

    public CircleGlideTransformation(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return PoetTransformationUtils.circleCrop(pool, toTransform);
    }

    @Override
    public String getId() {
        return this.getClass().getName();
    }
}
