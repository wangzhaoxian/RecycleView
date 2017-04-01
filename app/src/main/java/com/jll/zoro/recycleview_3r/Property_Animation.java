package com.jll.zoro.recycleview_3r;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Property_Animation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
    }

    public void rotateyAnimRun(final View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(view, pvhX, pvhY,pvhZ).setDuration(1000).start();
//        ObjectAnimator//
//                .ofFloat(view, "alpha",1,0,1,0,1,0,1)//
//                .setDuration(3500)//
//                .start();
//        ObjectAnimator anim = ObjectAnimator//
//                .ofFloat(view, "hhh", 1.0F, 0.0F)//
//                .setDuration(500);//
//        anim.start();
//        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float cVal = (Float) animation.getAnimatedValue();
//                view.setAlpha(cVal);
//                view.setScaleX(cVal);
//                view.setScaleY(cVal);
//            }
//        });
    }
}
