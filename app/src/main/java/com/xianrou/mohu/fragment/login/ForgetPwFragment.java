package com.xianrou.mohu.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 忘记密码
 */

public class ForgetPwFragment extends BaseFragment implements View.OnClickListener {

    private static ForgetPwFragment instance;
    private View mView;
    private RelativeLayout rlTop;
    private TextView tvTabRight;
    private EditText etInputNewPd;

    public static synchronized ForgetPwFragment getInstance() {
        if(instance==null)
            instance = new ForgetPwFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_forget_password, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        rlTop = (RelativeLayout) mView.findViewById(R.id.rl_top);
        tvTabRight = (TextView) mView.findViewById(R.id.tv_tab_right);
        etInputNewPd = (EditText) mView.findViewById(R.id.et_input_new_pd);
        mView.findViewById(R.id.rl_close).setOnClickListener(this);
        mView.findViewById(R.id.iv_see_password).setOnClickListener(this);
        mView.findViewById(R.id.btn_sure).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_close ://关闭
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.iv_see_password ://明文密码

                break;
            case R.id.btn_sure ://确定

                break;
        }
    }
}
