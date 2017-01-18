package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ActivityUtil;

/**
 * @author 咸鱼
 * @date 2017/1/18 0018
 * @des ${TODO}
 */

public class PublishVideoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_video);
        findViewById(R.id.btn_aa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.startActivity(PublishVideoActivity.this,DescriptionActivity.class, AppConfig.PUBLISH_VIDEO);
            }
        });
    }
}
