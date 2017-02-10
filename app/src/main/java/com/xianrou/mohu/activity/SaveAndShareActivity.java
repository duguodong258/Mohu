package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xianrou.mohu.R;
import com.xianrou.mohu.adapter.SaveAndShareRvAdapter;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.bean.PublishPhotoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/1/13
 * @des 保存并分享
 */

public class SaveAndShareActivity extends BaseActivity {

    private List<PublishPhotoEntity> mDatas;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveandshare);
        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_save_photo);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(layoutManager);
    }


    private void initData() {
        mDatas = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
           PublishPhotoEntity entity = new PublishPhotoEntity(R.drawable.zs);
           mDatas.add(entity);
        }
        PublishPhotoEntity entity = new PublishPhotoEntity(R.drawable.bgse);
        mDatas.add(entity);
        //从集合中拿出第一张设置给上面的imageview 剩下的传给adapter
        mRecyclerView.setAdapter(new SaveAndShareRvAdapter(mDatas,mContext));
    }
}
