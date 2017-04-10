package com.jll.zoro.recycleview_3r;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @Author : Zoro.
 * @Date : 2017/4/7.
 * @Describe :
 */

public class UpDown_ScrollView extends ScrollView {
    private OnScrollChangedListener listener;
    private boolean isScrolledToTop = true;// 初始化的时候设置一下值
    private boolean isScrolledToBottom = false;
    private boolean isScrolledToTop_1 = false;
    private boolean isScrolledToBottom_1 = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    isScrolledToTop_1 = false;
                    break;
                case 2:
                    isScrolledToBottom_1 = false;
                    break;
            }
//            super.handleMessage(msg);
        }
    };

    public UpDown_ScrollView(Context context) {
        super(context);
    }

    public void setOnScrollChangedListener(OnScrollChangedListener listener) {
        this.listener = listener;
    }

    public UpDown_ScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UpDown_ScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UpDown_ScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY == 0) {
            isScrolledToTop = clampedY;
            isScrolledToBottom = false;
        } else {
            isScrolledToTop = false;
            isScrolledToBottom = clampedY;
        }
        notifyScrollChangedListeners();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (oldt < t && ((t - oldt) > 5)) {// 向上
            listener.up();
        } else if (oldt > t && (oldt - t) > 5) {// 向下
            listener.down();
        }
        if (android.os.Build.VERSION.SDK_INT < 9) {  // API 9及之后走onOverScrolled方法监听
            if (getScrollY() == 0) {    // 小心踩坑1: 这里不能是getScrollY() <= 0
                isScrolledToTop = true;
                isScrolledToBottom = false;
            } else if (getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) {
                // 小心踩坑2: 这里不能是 >=
                // 小心踩坑3（可能忽视的细节2）：这里最容易忽视的就是ScrollView上下的padding　
                isScrolledToBottom = true;
                isScrolledToTop = false;
            } else {
                isScrolledToTop = false;
                isScrolledToBottom = false;
            }
            notifyScrollChangedListeners();
        }
        // 有时候写代码习惯了，为了兼容一些边界奇葩情况，上面的代码就会写成<=,>=的情况，结果就出bug了
        // 我写的时候写成这样：getScrollY() + getHeight() >= getChildAt(0).getHeight()
        // 结果发现快滑动到底部但是还没到时，会发现上面的条件成立了，导致判断错误
        // 原因：getScrollY()值不是绝对靠谱的，它会超过边界值，但是它自己会恢复正确，导致上面的计算条件不成立
        // 仔细想想也感觉想得通，系统的ScrollView在处理滚动的时候动态计算那个scrollY的时候也会出现超过边界再修正的情况
    }

    private void notifyScrollChangedListeners() {
        if (isScrolledToTop) {
            if (listener != null) {
//                if (!isScrolledToTop_1) {
                    listener.onScrolledToTop();
//                    isScrolledToTop_1 = true;
//                }
//                handler.sendEmptyMessageDelayed(1, 1000);
            }
        } else if (isScrolledToBottom) {
            if (listener != null) {
//                if (!isScrolledToBottom_1) {
                    listener.onScrolledToBottom();
//                    isScrolledToBottom_1 = true;
//                }
//                handler.sendEmptyMessageDelayed(2, 1000);
            }
        }
    }

    public boolean isScrolledToTop() {
        return isScrolledToTop;
    }

    public boolean isScrolledToBottom() {
        return isScrolledToBottom;
    }

    public interface OnScrollChangedListener {
        void up();

        void down();

        void onScrolledToBottom();

        void onScrolledToTop();
    }
}
