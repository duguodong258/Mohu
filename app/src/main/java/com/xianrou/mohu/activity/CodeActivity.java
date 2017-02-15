package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.bean.RecommendEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/1/14
 * @des 解封码
 */

public class CodeActivity extends BaseActivity implements View.OnClickListener, XRecyclerView.LoadingListener {

    private EditText etInputId;//输入识别码的edit
    private XRecyclerView mRecyclerView;
    private List<RecommendEntity> mDatas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        initView();
        getData();

    }

    private void initView() {
        mRecyclerView = (XRecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setAdapter(new RecycleViewAdapter());
        TextView titleCenter = (TextView) findViewById(R.id.title_center);
        etInputId = (EditText) findViewById(R.id.et_input_id);
        findViewById(R.id.ll_rightLayout).setOnClickListener(this);
        findViewById(R.id.ll_backLayout).setOnClickListener(this);
        findViewById(R.id.iv_clean_content).setOnClickListener(this);
        titleCenter.setText("解封码");
    }


    private void getData() {
        mDatas = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            RecommendEntity entity = new RecommendEntity(R.drawable.zs2,"郑爽 "+i);
            mDatas.add(entity);
        }
        RecycleViewAdapter adapter = new RecycleViewAdapter();
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_backLayout ://返回
                finish();
                break;
            case R.id.ll_rightLayout ://确定

                break;
            case R.id.iv_clean_content ://清除
                etInputId.setText("");
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }





    public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder>{

        @Override
        public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_code_rv_item, null);
            return new RecycleViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecycleViewHolder holder, int position) {
            RecommendEntity entity = mDatas.get(position);
//            holder.mIvImg.setImageResource(entity.getImg());
//            holder.mTvName.setText(entity.getName());
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }


    class RecycleViewHolder extends RecyclerView.ViewHolder{

//        private final TextView mTvName;
//        private final ImageView mIvImg;

        public RecycleViewHolder(View itemView) {
            super(itemView);
//            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
//            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
