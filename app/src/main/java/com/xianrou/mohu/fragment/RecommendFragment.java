package com.xianrou.mohu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 推荐
 */

public class RecommendFragment extends BaseFragment {

    private View mView;
    private XRecyclerView mRecyclerView;
    private Context mContext;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recommend, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (XRecyclerView) mView.findViewById(R.id.recycleView);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,2);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }


    private void getData() {

        RecommendAdapter adapter = new RecommendAdapter();
        mRecyclerView.setAdapter(adapter);
    }


    public class RecommendAdapter extends RecyclerView.Adapter<RecommendHolder>{

        @Override
        public RecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item, null);
            return new RecommendHolder(view);
        }

        @Override
        public void onBindViewHolder(RecommendHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }


    class RecommendHolder extends RecyclerView.ViewHolder{

        public RecommendHolder(View itemView) {
            super(itemView);
        }
    }
}
