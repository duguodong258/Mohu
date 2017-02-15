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
import com.xianrou.mohu.widget.CodeButton;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 修改密码
 */

public class ModifyPwFragment extends BaseFragment implements View.OnClickListener {

    private static ModifyPwFragment instance;
    private View mView;
    private RelativeLayout rlTop;
    private TextView tvTabRight;
    private TextView tvPhoneNumber;
    private EditText etInputCode;
    private CodeButton btnGetCode;

    public static synchronized ModifyPwFragment getInstance() {
        if(instance==null)
            instance = new ModifyPwFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_modify_password, null);
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
        tvPhoneNumber = (TextView) mView.findViewById(R.id.tv_phoneNumber);
        etInputCode = (EditText) mView.findViewById(R.id.et_input_code);
        btnGetCode = (CodeButton) mView.findViewById(R.id.btn_getCode);
        mView.findViewById(R.id.rl_close).setOnClickListener(this);
        mView.findViewById(R.id.btn_sure).setOnClickListener(this);
        tvTabRight.setOnClickListener(this);
        btnGetCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_close ://关闭

                break;
            case R.id.tv_tab_right ://登录

                break;
            case R.id.btn_getCode ://获取验证码

                break;
            case R.id.btn_sure ://登录

                break;
        }
    }
}
