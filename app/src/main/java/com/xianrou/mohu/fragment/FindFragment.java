package com.xianrou.mohu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 发现
 */

public class FindFragment extends BaseFragment {

    private static FindFragment instance;
    public static synchronized FindFragment getInstance() {
        if(instance==null)
            instance = new FindFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
