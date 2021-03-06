package com.johnfneto.newsexercise.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.johnfneto.newsexercise.R;
import com.johnfneto.newsexercise.TaskViewManager;
import com.johnfneto.newsexercise.models.Items;
import com.johnfneto.newsexercise.utils.ConnectivityInfo;
import com.johnfneto.newsexercise.view_models.ViewModel;
import com.johnfneto.newsexercise.views.MainActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by johnneto on 24/10/16.
 */

public class NetworkController {
    protected static final String TAG = "NetworkController";

    private static String url;

    private static NetworkController objNetworkController;
    private static MainActivity mActivity;
    private static TaskViewManager mDelegate;


    public static NetworkController getInstance() {
        if (objNetworkController == null) {
            objNetworkController = new NetworkController(); }
        return objNetworkController;
    }

    public static void initialize(MainActivity ctx, TaskViewManager delegate) {
        mActivity = ctx;
        mDelegate = delegate;
    }

    public static void loadFeed() {
        //new ViewModel(mActivity);
        if (ConnectivityInfo.isConnectedToInternet(mActivity))
            new LoadFeedTask().execute();
        else
            mDelegate.closeSwipeRefresh();
    }

    private static class LoadFeedTask extends AsyncTask<Void, Integer, Items> {
        protected Items doInBackground(Void... params) {

            Items items = null;
            url = mActivity.getString(R.string.url);

            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response responses = null;

                try {
                    responses = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                JSONObject Jobject = new JSONObject(responses.body().string());

                items = gson.fromJson(Jobject.toString(), Items.class);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return items;
        }

        protected void onProgressUpdate(Integer... progress) {
        }

        protected void onPostExecute(Items items) {

            /*for (Items.Item item : items.getRows()) {
                Log.d(TAG, "item :" + item.toString());
            }*/

            mDelegate.upDateActionBarTitle(items.getTitle());
            mDelegate.upDateList(items.getRows());
            mDelegate.notifyAdapter();
            mDelegate.closeSwipeRefresh();
        }
    }
}
