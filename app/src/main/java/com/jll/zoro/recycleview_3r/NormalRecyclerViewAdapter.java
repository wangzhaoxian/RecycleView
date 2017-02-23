package com.jll.zoro.recycleview_3r;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Zoro.
 * @Date : 2017/2/21.
 * @Describe :
 */

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> list = new ArrayList<>();

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    public NormalRecyclerViewAdapter(Context context, List<String> list) {
        this.list = list;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()){
            return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        }else{
            return new ImageTextViewHolder(mLayoutInflater.inflate(R.layout.item_image_text, parent, false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position==0?ITEM_TYPE.ITEM_TYPE_TEXT.ordinal():ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NormalTextViewHolder){
            Glide.with(mContext)
                    .load(list.get(position))
//                .animate(R.anim.item_alpha_in)
                    .thumbnail(0.1f)
                    .override(800, 800)
                    .into(((NormalTextViewHolder)holder).mImgView);
//                .transform(new GlideRoundTransform(mContext))
//                .into(holder.mImgView);
        }else{
            Glide.with(mContext)
                    .load(list.get(position))
//                .animate(R.anim.item_alpha_in)
                    .thumbnail(0.1f)
                    .override(800, 800)
                    .into(((ImageTextViewHolder)holder).mImgView);
//                .transform(new GlideRoundTransform(mContext))
//                .into(holder.mImgView);
            ((ImageTextViewHolder) holder).textView.setText(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgView;

        NormalTextViewHolder(View view) {
            super(view);
            mImgView = (ImageView) view.findViewById(R.id.img_View);
        }
    }
    public static class ImageTextViewHolder extends RecyclerView.ViewHolder {
        ImageView mImgView;
        TextView textView;

        ImageTextViewHolder(View view) {
            super(view);
            mImgView = (ImageView) view.findViewById(R.id.img_View);
            textView = (TextView) view.findViewById(R.id.tx_show);
        }
    }
}