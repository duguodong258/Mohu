package com.xianrou.mohu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;
import com.xianrou.mohu.bean.RecommendEntity;
import com.xianrou.mohu.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 推荐
 */

public class RecommendFragment extends BaseFragment implements XRecyclerView.LoadingListener {

    private View mView;
    private View mHeadView;
    private XRecyclerView mRecyclerView;
    private Context mContext;
    private List<RecommendEntity> mDatas;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recommend, null);
        mHeadView = inflater.inflate(R.layout.fragment_recommend_head,(ViewGroup)getActivity().findViewById(android.R.id.content),false);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (XRecyclerView) mView.findViewById(R.id.recycleView);
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadingListener(this);
//        mRecyclerView.addHeaderView(mHeadView);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }


    private void getData() {
        mDatas = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            RecommendEntity entity = new RecommendEntity(R.drawable.zs2,"郑爽 "+i);
            mDatas.add(entity);
        }
        RecommendAdapter adapter = new RecommendAdapter();
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }


    public class RecommendAdapter extends RecyclerView.Adapter<RecommendHolder>{

        @Override
        public RecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, null);
            return new RecommendHolder(view);
        }

        @Override
        public void onBindViewHolder(RecommendHolder holder, int position) {
            RecommendEntity entity = mDatas.get(position);
            holder.mIvImg.setImageResource(entity.getImg());
            holder.mTvName.setText(entity.getName());
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }


    class RecommendHolder extends RecyclerView.ViewHolder{

        private final TextView mTvName;
        private final ImageView mIvImg;

        public RecommendHolder(View itemView) {
            super(itemView);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mIvImg = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
