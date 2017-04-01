package com.jll.zoro.recycleview_3r;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        initList();
        activityRecycleView3 = (LinearLayout) findViewById(R.id.activity_recycle_view_3);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        listView = (RecyclerView) findViewById(R.id.list_View);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(gridLayoutManager);
        adapter = new RecyclerViewAdapter(this,list);
        listView.setAdapter(adapter);
        listView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                addList();
                adapter.setData(list);
                Log.i("RecycleView_3","RecycleView_3----"+currentPage);
            }
        });
//        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                Log.i("RecycleView_3","HAHAHAHA");
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Log.i("RecycleView_3","HEIEIEIEIE");
//
//            }
//        });
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listView.setAdapter(adapter);
                swipeContainer.setRefreshing(false);
            }
        });
    }

    private void initList() {
        for(int i=0;i<10;i++){
            list.add("HAHA--"+i);
        }
    }
    private void addList() {
        for(int i=0;i<10;i++){
            list.add("HAHA--"+i);
        }
    }

}
