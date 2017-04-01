package com.jll.zoro.recycleview_3r;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public abstract class BaseRecyclerAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter{

    public List<T> mList = new ArrayList<>();

    public abstract void onBindItemViewHolder(VH holder, T data, int position);
    public abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return onCreateItemViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mList.size() <= 0){
            return;
        }
        onBindItemViewHolder((VH)holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
