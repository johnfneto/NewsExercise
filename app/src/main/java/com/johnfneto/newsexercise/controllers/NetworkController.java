package com.johnfneto.newsexercise.controllers;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.johnfneto.newsexercise.models.Items;
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

    private static final String url = "https://dl.dropboxusercontent.com/u/746330/facts.json";

    private static NetworkController objNetworkController;


    public static NetworkController getInstance() {
        if (objNetworkController == null) {
            objNetworkController = new NetworkController(); }
        return objNetworkController;
    }

    public static void getFeed() {

        new GetFeedsTask().execute(null, null, null);
    }

    private static class GetFeedsTask extends AsyncTask<Void, Integer, Items> {
        protected Items doInBackground(Void... params) {

            Items items = null;

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

            for (Items.Item item : items.getRows()) {
                Log.d(TAG, "item :" + item.toString());
            }

        }
    }
}
