package com.xianrou.mohu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xianrou.mohu.AppConfig;
import com.xianrou.mohu.R;
import com.xianrou.mohu.base.BaseActivity;
import com.xianrou.mohu.util.ActivityUtil;
import com.xianrou.mohu.widget.province.WheelView;

/**
 * @author 咸鱼
 * @date 2017/1/18 0018
 * @des ${TODO}
 */

public class PublishVideoActivity extends BaseActivity {
    private WheelView mViewProvince;//省
    private WheelView mViewCity;    //市
    private WheelView mViewDistrict;//区
    private Button mBtnConfirm;     //确定

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_video);
        LinearLayout llroot = (LinearLayout) findViewById(R.id.ll_root);
        findViewById(R.id.btn_aa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtil.startActivity(PublishVideoActivity.this,DescriptionActivity.class, AppConfig.PUBLISH_VIDEO);
            }
        });

    }

}
