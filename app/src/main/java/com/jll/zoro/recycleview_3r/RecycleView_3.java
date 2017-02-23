package com.jll.zoro.recycleview_3r;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_3 extends AppCompatActivity {
    private LinearLayout activityRecycleView3;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView listView;
    private RecyclerViewAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_3);

        activityRecycleView3 = (LinearLayout) findViewById(R.id.activity_recycle_view_3);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        listView = (RecyclerView) findViewById(R.id.list_View);
        listView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
        listView.setAdapter(adapter);
        initList();
        swipeContainer.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        adapter = new RecyclerViewAdapter(this,list);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listView.setAdapter(adapter);
                swipeContainer.setRefreshing(false);
            }
        });
    }

    private void initList() {
        for(int i=0;i<30;i++){
            list.add("HAHA--"+i);
        }
    }

}
