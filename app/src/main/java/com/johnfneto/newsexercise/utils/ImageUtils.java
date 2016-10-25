package com.johnfneto.newsexercise.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by johnneto on 25/10/16.
 */

public class ImageUtils {

    public static int getScreenWidth(Context context) {
        int width;

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;

        return width;
    }

    public static int getImageWidth(Context context) {
        int ImageWidth;

        ImageWidth = getScreenWidth(context) / 5;

        return ImageWidth;
    }
}
