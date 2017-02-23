package com.jll.zoro.recycleview_3r;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.jll.zoro.rxjava_retrofit.HttpMethods;
import com.jll.zoro.rxjava_retrofit.ProgressSubscriber;
import com.jll.zoro.rxjava_retrofit.info.Subject;
import com.jll.zoro.rxjava_retrofit.interfaceInfo.SubscriberOnNextListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_2 extends AppCompatActivity {
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView gridRecycler;
    private View nodata,error;
    private HttpMethods httpMethods;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_2);
        init();
    }

    private void init() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        gridRecycler = (RecyclerView) findViewById(R.id.grid_recycler);
        gridRecycler.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        nodata = findViewById(R.id.nodata);
        error = findViewById(R.id.error);
        httpMethods = new HttpMethods();
        getData();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }
    private void getData() {
        ProgressSubscriber<List<Subject>> progressSubscriber = new ProgressSubscriber<List<Subject>>(
                new SubscriberOnNextListener<List<Subject>>() {
                    @Override
                    public void onNext(List<Subject> objectses) {
                        for (int i = 0; i < objectses.size(); i++) {
                            list.add(objectses.get(i).getCasts().get(0).getAvatars().getMedium());
                        }

                        gridRecycler.setAdapter(new NormalRecyclerViewAdapter(RecycleView_2.this, list));
                        int a = 1/0;
                    }
                },this){
            @Override
            public void onCompleted() {
                super.onCompleted();
                swipeContainer.setRefreshing(false);
                gridRecycler.setVisibility(View.GONE);
                nodata.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                swipeContainer.setRefreshing(false);
                swipeContainer.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                Button button = (Button) error.findViewById(R.id.request);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getData();
                    }
                });
            }
        };
        httpMethods.getTopMovie(progressSubscriber,0,10);
    }
    private void initRecycle() {
        gridRecycler.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        gridRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        gridRecycler.setAdapter(new NormalRecyclerViewAdapter(this, list));
    }

}
