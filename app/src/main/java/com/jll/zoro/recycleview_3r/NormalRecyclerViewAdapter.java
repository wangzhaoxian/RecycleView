package com.jll.zoro.recycleview_3r;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Zoro.
 * @Date : 2017/2/21.
 * @Describe :
 */

public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> list = new ArrayList<>();

    public NormalRecyclerViewAdapter(Context context) {
        initList();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    private void initList() {
        list.add("http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg");
        list.add("http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg");
        list.add("http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
        list.add("http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg");
        list.add("http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg");
        list.add("http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
        list.add("http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg");
        list.add("http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg");
        list.add("http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
        list.add("http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg");
        list.add("http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg");
        list.add("http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
        list.add("http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg");
        list.add("http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg");
        list.add("http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
        list.add("http://img4q.duitang.com/uploads/item/201408/11/20140811141753_iNtAF.jpeg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8b82b9014a90f603fa18d50f3912b31bb151edca.jpg");
        list.add("http://imgsrc.baidu.com/forum/pic/item/8e230cf3d7ca7bcb3acde5a2be096b63f724a8b2.jpg");
        list.add("http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg");
        list.add("http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg");
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
//        holder.mImgView.setImageUri(list.get(position));
//        holder.mImgView.setZoomable(false);
        Glide.with(mContext)
                .load(list.get(position))
//                .animate(R.anim.item_alpha_in)
                .thumbnail(0.1f)
                .override(800, 800)
//                .transform(new GlideRoundTransform(mContext))
                .into(holder.mImgView);
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
    class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;
        public GlideRoundTransform(Context context) {
            this(context, 10);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}