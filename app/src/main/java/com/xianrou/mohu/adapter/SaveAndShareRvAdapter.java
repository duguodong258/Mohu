package com.xianrou.mohu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xianrou.mohu.R;
import com.xianrou.mohu.bean.PublishPhotoEntity;

import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/2/7 0007
 * @des ${TODO}
 */

public class SaveAndShareRvAdapter extends RecyclerView.Adapter<SaveAndShareRvAdapter.SaveAndShareHolder> {

    private List<PublishPhotoEntity> mDatas;
    private Context mContext;
    private int mPosition;

    public SaveAndShareRvAdapter(List<PublishPhotoEntity> datas, Context context) {
        mDatas = datas;
        mContext = context;
    }

    @Override
    public SaveAndShareHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_saveandshare_item, null);
        return new SaveAndShareHolder(view);
    }

    @Override
    public void onBindViewHolder(SaveAndShareHolder holder, int position) {
        mPosition = position;
        holder.mImageView.setImageResource(mDatas.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class SaveAndShareHolder extends RecyclerView.ViewHolder{

        private ImageView mImageView;
        private TextView tv_last_position;

        public SaveAndShareHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            tv_last_position = (TextView) itemView.findViewById(R.id.tv_last_position);
            if(mPosition == 4){
                tv_last_position.setVisibility(View.VISIBLE);
            }
        }
    }
}
