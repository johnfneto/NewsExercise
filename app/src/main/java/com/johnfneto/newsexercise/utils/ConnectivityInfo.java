package com.johnfneto.newsexercise.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.johnfneto.newsexercise.R;

/**
 * Created by johnneto on 25/10/16.
 */

public class ConnectivityInfo {

    public static boolean connectivityAvailable = false;

    public static boolean isConnectedToInternet(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                connectivityAvailable = true;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                connectivityAvailable = true;
            }
        } else {
            connectivityAvailable = false;
            Toast.makeText(context, context.getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        }

        return connectivityAvailable;
    }
}
