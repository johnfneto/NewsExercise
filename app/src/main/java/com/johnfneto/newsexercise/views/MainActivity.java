package com.johnfneto.newsexercise.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

import com.johnfneto.newsexercise.R;
import com.johnfneto.newsexercise.adapters.ItemAdapter;
import com.johnfneto.newsexercise.controllers.NetworkController;
import com.johnfneto.newsexercise.models.Items;
import com.johnfneto.newsexercise.view_models.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView listView;
    private ActionBar actionBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ItemAdapter itemAdapter;

    private List<Items.Item> itemsList = new ArrayList<>();

    public static int width;

    public TaskViewManager delegate;

    public interface TaskViewManager {
        //Through this interface the event logic is
        //passed off to the ViewModel.
        void registerAdapter(ListView listView, ItemAdapter itemAdapter);
        void registerActionBar(ActionBar actionBar);
        void registerSwipeRefreshLayout(SwipeRefreshLayout mSwipeRefreshLayout);

        void notifyAdapter();

        void upDateList(List<Items.Item> itemsList);
        void upDateActionBarTitle(String title);
        void closeSwipeRefresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        width = displaymetrics.widthPixels;

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        listView = (ListView) findViewById(R.id.listView);

        //Items.prepareDummyContent(itemsList);

        itemAdapter = new ItemAdapter(MainActivity.this, itemsList);
        listView.setAdapter(itemAdapter);


        this.delegate = new ViewModel(MainActivity.this);
        this.delegate.registerAdapter(listView, itemAdapter);
        this.delegate.registerActionBar(actionBar);
        this.delegate.registerSwipeRefreshLayout(mSwipeRefreshLayout);

        if (itemsList.size() == 0) {
            //Log.d(TAG, "itemsList is empty");

            NetworkController.getFeed();
        }

    }
}
