package com.johnfneto.newsexercise.utils;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by johnneto on 27/10/16.
 */

public class UiUtils {

    private UiUtils()
    {
    }

    public static void setRefreshing(final SwipeRefreshLayout swipeRefreshLayout, final boolean isRefreshing)
    {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(isRefreshing);
                }
            });
        }
    }
}
