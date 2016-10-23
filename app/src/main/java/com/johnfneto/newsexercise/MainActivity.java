package com.johnfneto.newsexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.widget.ListView;
import com.johnfneto.newsexercise.adapters.ItemAdapter;
import com.johnfneto.newsexercise.models.Items;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView listView;
    private ActionBar actionBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ItemAdapter itemAdapter;

    private List<Items.Item> itemsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        listView = (ListView) findViewById(R.id.listView);

        Items.prepareDummyContent(itemsList);

        itemAdapter = new ItemAdapter(this, itemsList);
        listView.setAdapter(itemAdapter);
    }
}
