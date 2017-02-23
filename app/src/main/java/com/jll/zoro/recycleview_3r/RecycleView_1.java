package com.jll.zoro.recycleview_3r;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jll.zoro.rxjava_retrofit.HttpMethods;
import com.jll.zoro.rxjava_retrofit.ProgressSubscriber;
import com.jll.zoro.rxjava_retrofit.info.Subject;
import com.jll.zoro.rxjava_retrofit.interfaceInfo.SubscriberOnNextListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_1 extends AppCompatActivity {
    private static final String tag = "RecycleView_1";
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();
    private HttpMethods httpMethods;
    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_1);

        initRecycle();

        initHttp();

        getData();

    }

    private void getData() {
        ProgressSubscriber<List<Subject>> subscriber = new ProgressSubscriber<>(getTopMovieOnNext, this);
//        httpMethods.getTopMovie(new ProgressSubscriber(getTopMovieOnNext,this),0,10);
        httpMethods.getTopMovie(subscriber, 0, 10);
    }

    private void initHttp() {
        httpMethods = new HttpMethods();
        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> objectses) {
                for (int i = 0; i < objectses.size(); i++) {
                    list.add(objectses.get(i).getCasts().get(0).getAvatars().getMedium());
                    Log.i(tag, "ImgSrc==" + objectses.get(i).getCasts().get(0).getAvatars().getMedium());
                }

                recyclerView.setAdapter(new NormalRecyclerViewAdapter(RecycleView_1.this, list));
            }
        };
    }

    private void initRecycle() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        recyclerView.setAdapter(new NormalRecyclerViewAdapter(this, list));
    }
}

