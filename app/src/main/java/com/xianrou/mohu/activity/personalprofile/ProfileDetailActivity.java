package com.xianrou.mohu.activity.personalprofile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;

/**
 * @author 咸鱼
 * @date 2017/2/10 0010
 * @des 账号 支付宝 昵称 共用同一activity
 */

public class ProfileDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = (int) getIntent().getExtras().get("flag");//区分是哪个点击跳转过来的
        switch (flag) {
            case AppConfig.ACCOUNT :
                setContentView(R.layout.activity_profiledetail);
                EditText editText = (EditText) findViewById(R.id.et_profile);
                break;
            case AppConfig.ALIPAY :
                setContentView(R.layout.activity_alipay);
                break;
            case AppConfig.NICKNAME :

                break;

        }
    }
}
