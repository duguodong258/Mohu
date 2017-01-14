package com.xianrou.mohu.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;

/**
 * @author 咸鱼
 * @date 2017/1/13 0013
 * @des ${TODO}
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    SimpleDraweeView draweeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        draweeView = (SimpleDraweeView) findViewById(R.id.user_avator);
        findViewById(R.id.iv_login_qq).setOnClickListener(this);
        Uri uri = Uri.parse("http://image102.360doc.com/DownloadImg/2016/12/2900/88036894_1.jpg");
        draweeView.setImageURI(uri);
    }

    @Override
    public void onClick(View view) {

    }
}
