package com.johnfneto.newsexercise;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.widget.ListView;

import com.johnfneto.newsexercise.models.Items;

import java.util.List;

/**
 * Created by johnneto on 27/10/16.
 */

public interface TaskViewManager {
    //Through this interface the event logic is
    //passed off to the ViewModel.
    void registerAdapter(ListView listView);
    void registerActionBar(ActionBar actionBar);
    void registerSwipeRefreshLayout(SwipeRefreshLayout mSwipeRefreshLayout);

    void registerNetworkController();

    void notifyAdapter();

    void upDateList(List<Items.Item> itemsList);
    void upDateActionBarTitle(String title);
    void closeSwipeRefresh();

}
