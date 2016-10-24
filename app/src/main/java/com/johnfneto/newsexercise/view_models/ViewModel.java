package com.johnfneto.newsexercise.view_models;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.ListView;

import com.johnfneto.newsexercise.adapters.ItemAdapter;
import com.johnfneto.newsexercise.controllers.NetworkController;
import com.johnfneto.newsexercise.models.Items;
import com.johnfneto.newsexercise.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnneto on 24/10/16.
 */

public class ViewModel  implements MainActivity.TaskViewManager  {
    private static final String TAG = "ViewModel";

    private MainActivity mActivity;

    NetworkController networkController;

    private ItemAdapter itemAdapter;
    private ActionBar actionBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Items.Item> itemsList = new ArrayList<>();

    public ViewModel(MainActivity mActivity) {
        this.mActivity = mActivity;

        NetworkController.initialize(mActivity, mActivity.delegate);
        networkController = NetworkController.getInstance();
    }

    @Override
    public void registerAdapter(ListView listView, ItemAdapter itemAdapter) {
        itemAdapter = new ItemAdapter(mActivity, itemsList);
        listView.setAdapter(itemAdapter);
        this.itemAdapter = itemAdapter;
    }

    @Override
    public void registerActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    @Override
    public void registerSwipeRefreshLayout(final SwipeRefreshLayout mSwipeRefreshLayout) {
        swipeRefreshLayout = mSwipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshLayout.isRefreshing()) {
                    //mSwipeRefreshLayout.setRefreshing(false);
                    Log.d(TAG, "isRefreshing");
                }
                else
                    NetworkController.getFeed();
                NetworkController.getFeed();
            }

        });
    }

    @Override
    public void notifyAdapter() {
        itemAdapter.notifyDataSetChanged();
    }

    @Override
    public void upDateList(List<Items.Item> itemsList) {
        this.itemsList.clear();
        removeEmptyItems(itemsList);
        this.itemsList.addAll(itemsList);
    }

    private void removeEmptyItems(List<Items.Item> itemsList) {
        List<Integer> indexesToRemove = new ArrayList<>();

        int i = 0;
        for (Items.Item item : itemsList) {

            if (item.getTitle() == null && item.getDescription() == null && item.getImage() == null) {
                Log.d(TAG, "item :" + i + " is empty");

                indexesToRemove.add(i);
            }
            i++;
        }

        for (int j = 0; j < indexesToRemove.size(); j++) {
            Log.d(TAG, "index To Remove :" + indexesToRemove.get(j));

            itemsList.remove((int) indexesToRemove.get(j));
        }
    }

    @Override
    public void upDateActionBarTitle(String title) {
        actionBar.setTitle(title);
    }

    @Override
    public void closeSwipeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

}