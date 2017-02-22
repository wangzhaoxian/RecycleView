package com.jll.zoro.recycleview_3r;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jll.zoro.rxjava_retrofit.HttpMethods;
import com.jll.zoro.rxjava_retrofit.ProgressSubscriber;
import com.jll.zoro.rxjava_retrofit.info.Subject;
import com.jll.zoro.rxjava_retrofit.interfaceInfo.SubscriberOnNextListener;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String tag = "MainActivity";
    private HttpMethods httpMethods;
    private SubscriberOnNextListener getTopMovieOnNext;
    private TextView showData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showData = (TextView) findViewById(com.jll.zoro.rxjava_retrofit.R.id.showData);
        findViewById(R.id.show).setOnClickListener(this);
        findViewById(R.id.recycleView_1).setOnClickListener(this);
        findViewById(R.id.recycleView_2).setOnClickListener(this);
        findViewById(R.id.recycleView_3).setOnClickListener(this);
        httpMethods = new HttpMethods();
        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {

                showData.setText(subjects.get(9).getTitle());

            }
        };
    }

    //封装单独处理onNext()
    private void getData_2() {
        ProgressSubscriber<List<Subject>> subscriber = new ProgressSubscriber<>(getTopMovieOnNext, this);
//        httpMethods.getTopMovie(new ProgressSubscriber(getTopMovieOnNext,this),0,10);
        httpMethods.getTopMovie(subscriber, 0, 10);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recycleView_1:
                //TODO implement
                Intent intent = new Intent(MainActivity.this,RecycleView_1.class);
                this.startActivity(intent);
                break;
            case R.id.recycleView_2:
                //TODO implement
                Intent intent1 = new Intent(MainActivity.this,RecycleView_2.class);
                this.startActivity(intent1);
                break;
            case R.id.recycleView_3:
                //TODO implement
                Intent intent2 = new Intent(MainActivity.this,RecycleView_3.class);
                this.startActivity(intent2);
                break;
            case R.id.show:
                //TODO implement
                getData_2();
                break;
        }
    }
}
