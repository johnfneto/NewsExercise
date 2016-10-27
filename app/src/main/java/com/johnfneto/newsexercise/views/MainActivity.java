package com.johnfneto.newsexercise.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ListView;

import com.johnfneto.newsexercise.R;
import com.johnfneto.newsexercise.TaskViewManager;
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

    public TaskViewManager delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        listView = (ListView) findViewById(R.id.listView);

        this.delegate = new ViewModel(MainActivity.this);
        this.delegate.registerAdapter(listView);
        this.delegate.registerActionBar(actionBar);
        this.delegate.registerSwipeRefreshLayout(mSwipeRefreshLayout);

        this.delegate.registerNetworkController();

    }
}
