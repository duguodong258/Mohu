package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ActivityUtil;

/**
 * @author 咸鱼
 * @date 2017/1/13
 * @des ${TODO}
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtil.startActivity(SplashActivity.this,MainActivity.class,true);
            }
        }, 500);
    }
}
