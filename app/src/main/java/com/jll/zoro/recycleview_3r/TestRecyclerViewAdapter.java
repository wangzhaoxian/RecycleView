package com.jll.zoro.recycleview_3r;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jll.zoro.rxjava_retrofit.info.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Zoro.
 * @Date : 2017/2/24.
 * @Describe :
 */

public class TestRecyclerViewAdapter extends BaseLoadMoreAdapter<Subject, TestRecyclerViewAdapter.ViewHoulder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> list = new ArrayList<>();

    public TestRecyclerViewAdapter(Context context, List<String> list) {
        this.list = list;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindItemViewHolder(ViewHoulder holder, Subject data, int position) {
        if (holder instanceof ViewHoulder) {
            Glide.with(mContext)
                    .load(data.getCasts().get(1).getAvatars().getMedium())
                    .into(holder.imageView);
        }

    }

    @Override
    public ViewHoulder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ViewHoulder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    public class ViewHoulder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ViewHoulder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_View);
        }
    }
}
