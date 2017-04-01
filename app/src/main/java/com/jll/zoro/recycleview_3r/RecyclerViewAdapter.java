package com.jll.zoro.recycleview_3r;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Zoro.
 * @Date : 2017/2/21.
 * @Describe :
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> list = new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<String> list) {
        this.list = list;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageTextViewHolder(mLayoutInflater.inflate(R.layout.item_text_1, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ImageTextViewHolder) holder).textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public static class ImageTextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ImageTextViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.img_View);
        }
    }
}