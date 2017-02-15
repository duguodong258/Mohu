package com.xianrou.mohu.fragment.login;

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
 * @des 获取验证码
 */

public class GetCodeFragment extends BaseFragment {

    private static GetCodeFragment instance;
    public static synchronized GetCodeFragment getInstance() {
        if(instance==null)
            instance = new GetCodeFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getcode, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
