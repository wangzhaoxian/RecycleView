package com.jll.zoro.recycleview_3r;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
//ScrollView 上拉加载  下拉刷新（注意自定义的UpDown_ScrollView拉到顶或者底会执行很多遍，使用handler延迟发送就可以了）
public class ScrollVewActivity extends AppCompatActivity implements View.OnClickListener,UpDown_ScrollView.OnScrollChangedListener {
    private Button update, loadmore;
    private ListViewForScrollView listViewForScrollView;
    private ArrayAdapter adapter;
    private UpDown_ScrollView scrollView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_vew);
        initView();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 30; i++) {
            list.add("HAHAH===" + i);
        }
        adapter = new ArrayAdapter(ScrollVewActivity.this, android.R.layout.simple_list_item_1, list);
        listViewForScrollView.setAdapter(adapter);
    }

    private void initView() {
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);
        loadmore = (Button) findViewById(R.id.loadmore);
        loadmore.setOnClickListener(this);
        listViewForScrollView = (ListViewForScrollView) findViewById(R.id.listView);
        scrollView = (UpDown_ScrollView) findViewById(R.id.scrollView);
        scrollView.setOnScrollChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update:
//                list.clear();
//                for (int i = 30; i < 60; i++) {
//                    list.add("HAHAH===" + i);
//                }
//                adapter.notifyDataSetChanged();
                break;
            case R.id.loadmore:
//                for (int i = 3; i < 60; i++) {
//                    list.add("HAHAH===" + i);
//                }
//                adapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }

    @Override
    public void onScrolledToBottom() {
        for (int i = 60; i < 200; i++) {
            list.add("HAHAH===" + i);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onScrolledToTop() {
        list.clear();
        for (int i = 0; i < 60; i++) {
            list.add("HAHAH===" + i);
        }
        adapter.notifyDataSetChanged();

    }
}
