package com.xianrou.mohu.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseFragment;

/**
 * @author 咸鱼
 * @date 2017/1/19
 * @des 登录
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private static LoginFragment instance;
    private View mView;
    private LinearLayout llRoot;
    private RelativeLayout rlTop;
    private RelativeLayout rlClose;
    private TextView tvTabRight;
    private SimpleDraweeView userAvatar;
    private EditText etInputAccount;
    private ImageView ivSeeMoreAccount;
    private EditText etInputPassword;
    private ImageView ivSeePassword;
    private Button btnSureLogin;
    private TextView tvForgetPassword;

    public static synchronized LoginFragment getInstance() {
        if(instance==null)
            instance = new LoginFragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }


    private void initView() {
        llRoot = (LinearLayout) mView.findViewById(R.id.ll_root);
        rlTop = (RelativeLayout) mView.findViewById(R.id.rl_top);
        rlClose = (RelativeLayout) mView.findViewById(R.id.rl_close);
        tvTabRight = (TextView) mView.findViewById(R.id.tv_tab_right);
        userAvatar = (SimpleDraweeView) mView.findViewById(R.id.user_avatar);
        etInputAccount = (EditText) mView.findViewById(R.id.et_input_account);
        ivSeeMoreAccount = (ImageView) mView.findViewById(R.id.iv_see_more_account);
        etInputPassword = (EditText) mView.findViewById(R.id.et_input_password);
        ivSeePassword = (ImageView) mView.findViewById(R.id.iv_see_password);
        btnSureLogin = (Button) mView.findViewById(R.id.btn_sure_Login);
        tvForgetPassword = (TextView) mView.findViewById(R.id.tv_forget_password);
        tvTabRight.setText(R.string.register);
        rlClose.setOnClickListener(this);
        tvTabRight.setOnClickListener(this);
        userAvatar.setOnClickListener(this);
        ivSeeMoreAccount.setOnClickListener(this);
        ivSeePassword.setOnClickListener(this);
        btnSureLogin.setOnClickListener(this);
        tvForgetPassword.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_close ://关闭当前fragment

                break;
            case R.id.tv_tab_right ://注册
                replaceFragment(RegisterFragment.getInstance());
                break;
            case R.id.user_avatar ://头像

                break;
            case R.id.iv_see_more_account ://更多账号

                break;
            case R.id.iv_see_password ://明文显示密码

                break;
            case R.id.tv_forget_password ://忘记密码
                replaceFragment(ForgetPwFragment.getInstance());
                break;
        }
    }

    /**
     * 更换Fragment
     * @param fragment 要更换的fragment
     */
    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left).
                replace(R.id.fl_fragment_container,fragment).
                commitAllowingStateLoss();
    }
}
