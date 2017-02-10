package com.xianrou.mohu.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xianrou.mohu.R;
import com.xianrou.mohu.bean.PublishPhotoEntity;
import com.xianrou.mohu.events.BlurEvent;
import com.xianrou.mohu.util.BlurUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/2/6 0006
 * @des ${TODO}
 */

public class PublishPhotoAdapter extends RecyclerView.Adapter<PublishPhotoAdapter.PublishPhotoHolder> {

    private List<PublishPhotoEntity> mDatas;
    private int mPosition;
    private Context mContext;
    private PublishPhotoHolder mHolder;
    private Bitmap mBitmap;

    public PublishPhotoAdapter(List<PublishPhotoEntity> datas, Context context) {
        mDatas = datas;
        mContext = context;
        EventBus.getDefault().register(this);
    }

    @Override
    public PublishPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_publishphoto_item, null);
        mHolder = new PublishPhotoHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(PublishPhotoHolder holder, int position) {
        mPosition = position;
        PublishPhotoEntity entity = mDatas.get(position);
        holder.img_source.setImageResource(entity.getImage());
        //获取图片转成位图设置死高斯模糊度 滑动时候通过改变透明度来改变模糊度
        Drawable drawable = mContext.getResources().getDrawable(entity.getImage());
        mBitmap = ((BitmapDrawable) drawable).getBitmap();
        mBitmap = BlurUtil.fastblur(mContext, mBitmap, 100);
        mHolder.img_blur.setImageBitmap(mBitmap);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final BlurEvent event) {
        int blur = event.getBlur();//实时改变的透明度
        mHolder.img_blur.setAlpha(blur);
    }

    class PublishPhotoHolder extends RecyclerView.ViewHolder{
        private ImageView img_source;
        private ImageView img_blur;
        private View view_bg;

        public PublishPhotoHolder(View itemView) {
            super(itemView);
            img_source = (ImageView) itemView.findViewById(R.id.iv_photo_source);
            img_blur = (ImageView) itemView.findViewById(R.id.iv_photo_blur);
            view_bg = itemView.findViewById(R.id.photo_bg);
            itemView.findViewById(R.id.fl_root).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mPosition != 1){
                        if(view_bg.getVisibility() == View.VISIBLE){
                            view_bg.setVisibility(View.GONE);
                        }else{
                            view_bg.setVisibility(View.VISIBLE);
                        }
                    }
                }
            });
        }
    }
}
