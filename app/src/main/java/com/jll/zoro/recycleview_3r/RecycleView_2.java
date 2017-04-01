package com.jll.zoro.recycleview_3r;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jll.zoro.rxjava_retrofit.HttpMethods;
import com.jll.zoro.rxjava_retrofit.ProgressSubscriber;
import com.jll.zoro.rxjava_retrofit.info.Subject;
import com.jll.zoro.rxjava_retrofit.interfaceInfo.SubscriberOnNextListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleView_2 extends AppCompatActivity implements Callback {
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView gridRecycler;
    private View nodata, error;
    private HttpMethods httpMethods;
    private List<String> list = new ArrayList<>();
    private NormalRecyclerViewAdapter adapter;
    ProgressSubscriber<List<Subject>> progressSubscriber;
    private LinearLayout music;
    private TranslateAnimation mShowAction;
    private TranslateAnimation mHiddenAction;
    private boolean isShow_1 = false;
    private boolean isHide = false;
    private Handler handler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_2);
        mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(200);
        mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
        mHiddenAction.setDuration(200);
        handler.sendEmptyMessage(1);
        init();
    }

    private void init() {
        music = (LinearLayout) findViewById(R.id.music);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        gridRecycler = (RecyclerView) findViewById(R.id.grid_recycler);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        gridRecycler.setLayoutManager(gridLayoutManager);//这里用线性显示 类似于listview
        nodata = findViewById(R.id.nodata);
        error = findViewById(R.id.error);
        httpMethods = new HttpMethods();
        getData(1);
//        gridRecycler.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager) {
//            @Override
//            public void onLoadMore(int currentPage) {
//                getData(2);
//                Log.i("RecycleView_3", "RecycleView_3----" + currentPage);
//            }
//        });
        gridRecycler.addOnScrollListener(new BaseOnScrollListener() {
            @Override
            public void onLoadMore() {
                getData(2);
            }

            @Override
            public void musicShow(boolean isShow) {
                if (isShow) {
                    if (!isShow_1) {
                        music.startAnimation(mShowAction);
                        music.setVisibility(View.VISIBLE);
                        isShow_1 = true;
                        isHide = false;
                    }
                } else {
                    if (!isHide) {
                        music.startAnimation(mHiddenAction);
                        music.setVisibility(View.GONE);
                        isShow_1 = false;
                        isHide = true;
                    }
                }
            }
        });
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData(1);
            }
        });
    }

    public class MyHandler extends Handler {
        MyHandler(Callback callback) {
            super(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i("MyHandler", "MyHandlerMyHandler=====void");
        }
    }

    private void getData(final int i) {
        progressSubscriber = new ProgressSubscriber<List<Subject>>(
                new SubscriberOnNextListener<List<Subject>>() {
                    @Override
                    public void onNext(List<Subject> objectses) {
                        for (int i = 0; i < objectses.size(); i++) {
                            list.add(objectses.get(i).getCasts().get(0).getAvatars().getMedium());
                        }
                        if (adapter == null) {
                            adapter = new NormalRecyclerViewAdapter(RecycleView_2.this, list);
//                            adapter.addRefreshData(objectses);
                            gridRecycler.setAdapter(adapter);
                        } else {
//                            if (i == 1) {
//                                adapter.addRefreshData(objectses);
//                            } else {
//                                adapter.addLoadMoreData(objectses);
//                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }, this) {
            @Override
            public void onCompleted() {
                super.onCompleted();
                swipeContainer.setRefreshing(false);
                Log.i("RecycleView_2", "RecycleView_2RecycleView_2");
//                gridRecycler.setVisibility(View.GONE);
//                nodata.setVisibility(View.VISIBLE);
//                error.setVisibility(View.GONE);
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
                        getData(1);
                    }
                });
            }
        };
        httpMethods.getTopMovie(progressSubscriber, 0, 10);
    }

    private void initRecycle() {
        gridRecycler.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        gridRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        gridRecycler.setAdapter(new NormalRecyclerViewAdapter(this, list));
    }

    @Override
    public boolean handleMessage(Message message) {
        Log.i("MyHandler", "MyHandlerMyHandler=====boolean");
        return true;
    }
}
