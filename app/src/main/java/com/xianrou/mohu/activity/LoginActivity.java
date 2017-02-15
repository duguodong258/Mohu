package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.fragment.login.LoginFragment;
import com.xianrou.mohu.util.ToastUtil;

/**
 * @author 咸鱼
 * @date 2017/1/13 0013
 * @des 登录页
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlRoot;
    private FrameLayout flFragmentContainer;//fragment容器

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        flFragmentContainer = (FrameLayout) findViewById(R.id.fl_fragment_container);
        findViewById(R.id.iv_login_xl).setOnClickListener(this);
        findViewById(R.id.iv_login_wx).setOnClickListener(this);
        findViewById(R.id.iv_login_qq).setOnClickListener(this);
        //默认进入登录fragment
        LoginFragment loginFragment = LoginFragment.getInstance();
        getSupportFragmentManager().beginTransaction().addToBackStack("login").replace(R.id.fl_fragment_container,loginFragment).commitAllowingStateLoss();
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_xl ://微博登录
                ToastUtil.showToast(mContext,"微博登录");
                break;
            case R.id.iv_login_wx ://微信登录
                ToastUtil.showToast(mContext,"微信登录");
                break;
            case R.id.iv_login_qq ://QQ登录
                ToastUtil.showToast(mContext,"QQ登录");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(getSupportFragmentManager().getBackStackEntryCount() > 1){
                getSupportFragmentManager().popBackStack();
            }
        }
        return false;
    }

    /**
     * 捕获keyBack
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return true;
    }
}
