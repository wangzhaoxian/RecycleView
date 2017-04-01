package com.jll.zoro.recycleview_3r;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @Author : Zoro.
 * @Date : 2017/2/24.
 * @Describe :
 */

public abstract class BaseOnScrollListener extends RecyclerView.OnScrollListener {
    protected int lastVisibleItem;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //dy <0 表示 上滑， dy>0 表示下滑
        if (dy < -3) {
            musicShow(true);
        } else if (dy > 3) {
            musicShow(false);
        }
        if (!recyclerView.canScrollVertically(1)) {
            onLoadMore();
        }
        lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int i = recyclerView.getAdapter().getItemCount();
        if (newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
            //int i = recyclerView.getAdapter().getItemCount();
//            onLoadMore();
        }
    }

    public abstract void onLoadMore();

    public abstract void musicShow(boolean isShow);
}

