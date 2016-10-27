package com.johnfneto.newsexercise.view_models;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.ListView;

import com.johnfneto.newsexercise.TaskViewManager;
import com.johnfneto.newsexercise.adapters.ItemAdapter;
import com.johnfneto.newsexercise.controllers.NetworkController;
import com.johnfneto.newsexercise.models.Items;
import com.johnfneto.newsexercise.utils.UiUtils;
import com.johnfneto.newsexercise.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnneto on 24/10/16.
 */

public class ViewModel  implements TaskViewManager {
    private static final String TAG = "ViewModel";

    private MainActivity mActivity;

    NetworkController networkController;

    private ItemAdapter itemAdapter;
    private ActionBar actionBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Items.Item> itemsList = new ArrayList<>();

    public ViewModel(MainActivity mActivity) {
        this.mActivity = mActivity;


    }

    @Override
    public void registerNetworkController() {
        NetworkController.initialize(mActivity, mActivity.delegate);
        networkController = NetworkController.getInstance();

        if (itemsList.size() == 0) {
            UiUtils.setRefreshing(swipeRefreshLayout, true);
            NetworkController.loadFeed();
        }
    }



    @Override
    public void registerAdapter(ListView listView) {
        itemAdapter = new ItemAdapter(mActivity, itemsList);
        listView.setAdapter(itemAdapter);
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

                NetworkController.loadFeed();
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

        for (int j = itemsList.size() - 1; j >= 0; j--) {
            if (itemsList.get(j).getTitle() == null && itemsList.get(j).getDescription() == null && itemsList.get(j).getImage() == null) {
                itemsList.remove(j);
            }
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